package com.stevensgv.inventory_service.service;

import com.stevensgv.inventory_service.dto.InventoryDTO;
import com.stevensgv.inventory_service.model.Inventory;

import java.util.List;
import java.util.Map;

public interface IInventoryService {

    List<Inventory> getInventoryList();

    Inventory findInventoryById(Long id);

    List<InventoryDTO> validateInventoryStock(Map<Long, Integer> inventoryStockList);

    void discountInventory(Map<Long, Integer> inventoryStockList);

    void saveInventory(Inventory inventory);

    void updateInventory(Long id, Inventory inventory);

    void deleteInventory(Long id);
}
