package com.logistic.logistic.features.product.dto;

import com.logistic.logistic.features.supplier.SupplierResponse;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double weight,
        String dimensions,
        SupplierResponse supplier
) {
}
