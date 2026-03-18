package com.stevensgv.inventory_service.controller;

import com.stevensgv.inventory_service.model.Inventory;
import com.stevensgv.inventory_service.service.IInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {

    private final IInventoryService inventoryService;

    @GetMapping
    public List<Inventory> getInventoryList() {
        return inventoryService.getInventoryList();
    }

    @PostMapping
    public Inventory createInventory(@Valid @RequestBody Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return inventoryService.findInventoryById(inventory.getId());
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id,
                                     @Valid @RequestBody Inventory inventory) {
        inventoryService.updateInventory(id, inventory);
        return inventoryService.findInventoryById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
