package com.logistic.logistic.features.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SupplierRequest(
        @NotBlank(message = "Supplier name is required")
        String name,

        @NotBlank(message = "Contact person is required")
        String contactPerson,

        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^[0-9]{9,15}$", message = "Invalid phone number")
        String phone,

        String address
) {
}
