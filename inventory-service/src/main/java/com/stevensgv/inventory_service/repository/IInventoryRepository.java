package com.stevensgv.inventory_service.repository;

import com.stevensgv.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Long> {
}
