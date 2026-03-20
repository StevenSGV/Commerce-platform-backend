package com.stevensgv.inventory_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Long productId;
    private Integer requestedQuantity;
    private Integer availableStock;
    private boolean available;
}
