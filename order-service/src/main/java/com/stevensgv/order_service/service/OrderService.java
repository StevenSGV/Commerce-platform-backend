package com.stevensgv.order_service.service;

import com.stevensgv.order_service.dto.InventoryDTO;
import com.stevensgv.order_service.dto.ProductDTO;
import com.stevensgv.order_service.exception.InsufficientStockException;
import com.stevensgv.order_service.exception.NotFoundException;
import com.stevensgv.order_service.model.Order;
import com.stevensgv.order_service.model.OrderItem;
import com.stevensgv.order_service.model.OrderStatus;
import com.stevensgv.order_service.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final ProductCircuitBreaker productCircuitBreaker;
    private final InventoryCircuitBreaker inventoryCircuitBreaker;

    @Override
    public List<Order> getOrderList() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order Not Found"));
    }

    @Override
    public void saveOrder(Order order) {
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("A new order cannot be created with CANCELLED status.");
        }

        Set<Long> productIds = order.getOrderItems()
                .stream()
                .map(OrderItem::getProductId)
                .collect(Collectors.toSet());

        List<ProductDTO> existingProducts = productCircuitBreaker.validateProductsCircuitBreaker(productIds);

        if (existingProducts.size() != productIds.size()) {
            throw new NotFoundException("One or more requested products were not found.");
        }

        BigDecimal total = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getUnitPrice()
                        .multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        if (order.getStatus() == OrderStatus.PAID) {
            Map<Long, Integer> inventoryStockList = order.getOrderItems()
                    .stream()
                    .collect(Collectors.toMap(
                            OrderItem::getProductId,
                            OrderItem::getQuantity
                    ));

            List<InventoryDTO> validatedStock = inventoryCircuitBreaker.validateStockWithCircuitBreaker(inventoryStockList);

            validatedStock.stream()
                    .filter(inventoryDTO -> !inventoryDTO.isAvailable())
                    .findFirst()
                    .ifPresent(inventoryDTO -> {
                        throw new InsufficientStockException(
                                "Insufficient stock for product ID: " + inventoryDTO.getProductId()
                        );
                    });

            inventoryCircuitBreaker.discountInventoryWithCircuitBreaker(inventoryStockList);
        }

        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));

        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id, Order order) {
        Order orderFound = this.findOrderById(id);

        orderFound.setUserId(order.getUserId());
        orderFound.setStatus(order.getStatus());
        orderFound.setTotal(order.getTotal());
        orderFound.setOrderItems(order.getOrderItems());

        orderRepository.save(orderFound);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
