package com.logistic.logistic.features.order;

import com.logistic.logistic.features.order.dto.OrderRequest;
import com.logistic.logistic.features.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Integer id);

    OrderResponse createOrder(OrderRequest orderRequest);


}