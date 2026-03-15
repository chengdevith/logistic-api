package com.logistic.logistic.features.inventory.dto;

import com.logistic.logistic.features.product.dto.ProductResponse;

public record InventoryResponse(
        Integer id,
        Integer quantityAvailable,
        String location,
        ProductResponse product
) {
}
