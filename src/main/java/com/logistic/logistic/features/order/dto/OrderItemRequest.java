package com.logistic.logistic.features.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(
        @NotNull(message = "Product id is required")
        Integer productId,

        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        Integer quantity,

        @NotNull(message = "Unit price is required")
        @Positive(message = "Unit price must be positive")
        Double price
) {
}
