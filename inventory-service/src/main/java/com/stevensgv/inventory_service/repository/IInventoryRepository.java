package com.stevensgv.inventory_service.repository;

import com.stevensgv.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByProductId(Set<Long> productIds);
}
