package com.stevensgv.order_service.controller;

import com.stevensgv.order_service.model.Order;
import com.stevensgv.order_service.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getOrders() {
        return orderService.getOrderList();
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        orderService.saveOrder(order);
        return orderService.findOrderById(order.getId());
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id,
                             @Valid @RequestBody Order order) {
        orderService.updateOrder(id, order);
        return orderService.findOrderById(id);
    }
}
