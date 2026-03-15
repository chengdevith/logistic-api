package com.logistic.logistic.features.order.dto;

import com.logistic.logistic.features.customer.dto.CustomerResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Integer id,
        LocalDateTime orderDate,
        String status,
        Double totalAmount,
        CustomerResponse customer,
        List<OrderItemResponse> orderItems
) {
}
