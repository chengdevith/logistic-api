package com.logistic.logistic.features.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequest(

        @NotNull(message = "Total amount is required")
        @Positive(message = "Total amount must be positive")
        Double totalAmount,

        @NotNull(message = "Customer id is required")
        Integer customerId,

        @NotNull(message = "Order items are required")
        List<OrderItemRequest> orderItems
) {
}
