package com.logistic.logistic.features.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        String name,

        String description,

        @NotNull(message = "Weight is required")
        @Positive(message = "Weight must be positive")
        Double weight,

        String dimensions,

        @NotNull(message = "Supplier id is required")
        Integer supplierId
) {
}
