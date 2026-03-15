package com.logistic.logistic.features.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateSupplierRequest(
        String name,
        String contactPerson,

        @Email(message = "Invalid email format")
        String email,

        @Pattern(regexp = "^[0-9]{9,15}$", message = "Invalid phone number")
        String phone,

        String address
) {
}
