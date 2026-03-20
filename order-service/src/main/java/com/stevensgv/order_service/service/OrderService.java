package com.stevensgv.order_service.service;

import com.stevensgv.order_service.dto.InventoryDTO;
import com.stevensgv.order_service.dto.ProductDTO;
import com.stevensgv.order_service.exception.InsufficientStockException;
import com.stevensgv.order_service.exception.NotFoundException;
import com.stevensgv.order_service.model.Order;
import com.stevensgv.order_service.model.OrderItem;
import com.stevensgv.order_service.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final IProductFeign productFeign;
    private final IInventoryFeign inventoryFeign;

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
        Set<Long> listProductId = order.getOrderItems()
                .stream()
                .map(OrderItem::getProductId)
                .collect(Collectors.toSet());

        List<ProductDTO> existingProducts = productFeign.validateProductList(listProductId);

        if (existingProducts.size() != listProductId.size()) {
            throw new NotFoundException("One or more requested products were not found.");
        }

        Map<Long, Integer> inventoryStockList = order.getOrderItems()
                .stream()
                .collect(Collectors.toMap(
                        OrderItem::getProductId,
                        OrderItem::getQuantity));

        List<InventoryDTO> validatedStock = inventoryFeign.validateStock(inventoryStockList);

        for (InventoryDTO inventoryDTO : validatedStock) {
            if (!inventoryDTO.isAvailable()) {
                throw new InsufficientStockException("Insufficient Stock for product ID: " + inventoryDTO.getProductId());
            }
        }

        inventoryFeign.discountInventory(inventoryStockList);
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
