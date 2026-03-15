package com.logistic.logistic.features.supplier.dto;

public record SupplierResponse(
        Integer id,
        String name,
        String contactPerson,
        String email,
        String phone,
        String address
) {
}
