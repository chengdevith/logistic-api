package com.logistic.logistic.features.supplier;

public record SupplierResponse(
        Integer id,
        String name,
        String email,
        String phone
) {
}
