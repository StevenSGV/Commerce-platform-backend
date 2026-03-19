package com.stevensgv.order_service.service;

import com.stevensgv.order_service.model.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getOrderList();

    Order findOrderById(Long id);

    void saveOrder(Order order);

    void updateOrder(Long id, Order order);

    void deleteOrder(Long id);
}
