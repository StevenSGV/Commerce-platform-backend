package com.stevensgv.inventory_service.service;

import com.stevensgv.inventory_service.dto.InventoryDTO;
import com.stevensgv.inventory_service.exception.InsufficientStockException;
import com.stevensgv.inventory_service.exception.NotFoundException;
import com.stevensgv.inventory_service.model.Inventory;
import com.stevensgv.inventory_service.repository.IInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<InventoryDTO> validateInventoryStock(Map<Long, Integer> inventoryStockList) {
        List<Inventory> inventoryList = inventoryRepository.findByProductIdIn(inventoryStockList.keySet());

        Map<Long, Inventory> inventoryMap = inventoryList.stream()
                .collect(Collectors.toMap(
                        Inventory::getProductId,
                        inventory -> inventory
                ));

        return inventoryStockList.entrySet().stream()
                .map(entry -> {
            Long productId = entry.getKey();
            Integer requestedQuantity = entry.getValue();
            Inventory inventory = inventoryMap.get(productId);

            if (inventory == null) {
                return new InventoryDTO(
                        productId,
                        requestedQuantity,
                        0,
                        false);
            }

            return new InventoryDTO(productId,
                    requestedQuantity,
                    inventory.getStock(),
                    inventory.getStock() >= requestedQuantity);
        }).toList();
    }

    @Override
    public void discountInventory(Map<Long, Integer> inventoryStockList) {
        List<Inventory> inventoryList = inventoryRepository.findByProductIdIn(inventoryStockList.keySet());

        Map<Long, Inventory> inventoryMap = inventoryList.stream()
                .collect(Collectors.toMap(
                        Inventory::getProductId,
                        inventory -> inventory));

        inventoryStockList.forEach((productId, quantityToDiscount) -> {
            Inventory inventory = inventoryMap.get(productId);

            if (inventory == null) {
                throw new NotFoundException("Inventory not found for product ID: " + productId);
            }

            if (inventory.getStock() < quantityToDiscount) {
                throw new InsufficientStockException("Insufficient stock for product ID: " + productId);
            }

            inventory.setStock(inventory.getStock() - quantityToDiscount);
        });

        inventoryRepository.saveAll(inventoryList);
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
