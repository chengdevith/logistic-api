package com.logistic.logistic.features.customer.dto;

import java.time.LocalDateTime;

public record CustomerResponse(
    Integer id,
    String name,
    String email,
    String phone,
    String address,
    LocalDateTime createdAt
) {
}
