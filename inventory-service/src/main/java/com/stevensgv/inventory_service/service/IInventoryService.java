package com.stevensgv.inventory_service.service;

import com.stevensgv.inventory_service.model.Inventory;

import java.util.List;

public interface IInventoryService {

    List<Inventory> getInventoryList();

    Inventory findInventoryById(Long id);

    void saveInventory(Inventory inventory);

    void updateInventory(Long id, Inventory inventory);

    void deleteInventory(Long id);
}
