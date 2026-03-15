package com.logistic.logistic.features.shipment.dto;

import com.logistic.logistic.features.order.dto.OrderResponse;

import java.time.LocalDateTime;

public record ShipmentResponse(
        Integer id,
        LocalDateTime shipmentDate,
        String carrier,
        String trackingNumber,
        String status,
        OrderResponse order
) {
}
