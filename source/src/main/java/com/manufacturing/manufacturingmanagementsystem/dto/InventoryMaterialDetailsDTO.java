package com.manufacturing.manufacturingmanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMaterialDetailsDTO {

    private String inventoryId;

    private String materialId;

    private Integer quantity;

    private Integer safetyStockAmount;
}