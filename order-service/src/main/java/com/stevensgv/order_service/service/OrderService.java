package com.stevensgv.order_service.service;

import com.stevensgv.order_service.exception.NotFoundException;
import com.stevensgv.order_service.model.Order;
import com.stevensgv.order_service.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;

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
