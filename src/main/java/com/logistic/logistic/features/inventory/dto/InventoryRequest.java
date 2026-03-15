package com.logistic.logistic.features.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record InventoryRequest(
        @NotNull(message = "Quantity is required")
        @PositiveOrZero(message = "Quantity cannot be negative")
        Integer quantityAvailable,

        String location,

        @NotNull(message = "Product id is required")
        Integer productId
) {
}
