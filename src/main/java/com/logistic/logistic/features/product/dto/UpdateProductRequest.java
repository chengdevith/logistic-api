package com.logistic.logistic.features.product.dto;

import jakarta.validation.constraints.Positive;

public record UpdateProductRequest(
        String name,
        String description,
        @Positive(message = "Weight must be positive")
        Double weight,
        String dimensions,
        Integer supplierId
) {
}
