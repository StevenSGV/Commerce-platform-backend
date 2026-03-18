package com.stevensgv.inventory_service.service;

import com.stevensgv.inventory_service.exception.NotFoundException;
import com.stevensgv.inventory_service.model.Inventory;
import com.stevensgv.inventory_service.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService implements IInventoryService{

    private final IInventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getInventoryList() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory with id " + id + " not found"));
    }

    @Override
    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public void updateInventory(Long id, Inventory inventory) {
        Inventory inventoryFound = this.findInventoryById(id);

        inventoryFound.setProductId(inventory.getProductId());
        inventoryFound.setStock(inventory.getStock());

        inventoryRepository.save(inventoryFound);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
