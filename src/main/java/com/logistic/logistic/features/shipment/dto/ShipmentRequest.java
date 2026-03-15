package com.logistic.logistic.features.shipment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShipmentRequest(

        @NotBlank(message = "Carrier is required")
        String carrier,

        String trackingNumber,

        @NotBlank(message = "Status is required")
        String status,

        @NotNull(message = "Order id is required")
        Integer orderId
) {
}
