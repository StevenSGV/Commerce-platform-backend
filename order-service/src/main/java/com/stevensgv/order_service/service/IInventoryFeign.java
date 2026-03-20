package com.stevensgv.order_service.service;

import com.stevensgv.order_service.dto.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "inventory-service")
public interface IInventoryFeign {

    @PostMapping("/api/inventories/stock")
    List<InventoryDTO> validateStock(@RequestBody Map<Long, Integer> inventoryStockList);

    @PostMapping("/api/inventories/discount")
    void discountInventory(@RequestBody Map<Long, Integer> inventoryStockList);
}
