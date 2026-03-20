package com.stevensgv.order_service.service;

import com.stevensgv.order_service.dto.InventoryDTO;
import com.stevensgv.order_service.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InventoryCircuitBreaker {

    private final IInventoryFeign inventoryFeign;

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "validateStockFallback")
    @Retry(name = "inventoryService")
    public List<InventoryDTO> validateStockWithCircuitBreaker(Map<Long, Integer> inventoryStockList) {
        return inventoryFeign.validateStock(inventoryStockList);
    }

    public List<InventoryDTO> validateStockFallback(Map<Long, Integer> inventoryStockList, Throwable throwable) {
        throw new ServiceUnavailableException("Inventory service is currently unavailable.");
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "discountInventoryFallback")
    @Retry(name = "inventoryService")
    public void discountInventoryWithCircuitBreaker(Map<Long, Integer> inventoryStockList) {
        inventoryFeign.discountInventory(inventoryStockList);
    }

    public void discountInventoryFallback(Map<Long, Integer> inventoryStockList, Throwable throwable) {
        throw new ServiceUnavailableException("Inventory service is currently unavailable for inventory update.");
    }
}
