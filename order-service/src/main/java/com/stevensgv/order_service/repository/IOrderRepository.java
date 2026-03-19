package com.stevensgv.order_service.repository;

import com.stevensgv.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
