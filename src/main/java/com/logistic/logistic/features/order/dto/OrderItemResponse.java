package com.logistic.logistic.features.order.dto;

import com.logistic.logistic.features.product.dto.ProductResponse;

public record OrderItemResponse(
        Integer id,
        Integer quantity,
        Double price,
        ProductResponse product
) {
}
