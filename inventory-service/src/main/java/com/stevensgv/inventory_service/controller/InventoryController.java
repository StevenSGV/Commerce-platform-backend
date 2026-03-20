package com.stevensgv.inventory_service.controller;

import com.stevensgv.inventory_service.dto.InventoryDTO;
import com.stevensgv.inventory_service.model.Inventory;
import com.stevensgv.inventory_service.service.IInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/stock")
    public List<InventoryDTO> validateStock(@RequestBody Map<Long, Integer> inventoryStockList){
        return inventoryService.validateInventoryStock(inventoryStockList);
    }

    @PostMapping("/discount")
    public ResponseEntity<Void> discountInventory(@RequestBody Map<Long, Integer> inventoryStockList) {
        inventoryService.discountInventory(inventoryStockList);
        return ResponseEntity.ok().build();
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
