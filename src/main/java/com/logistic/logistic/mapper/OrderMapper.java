package com.logistic.logistic.mapper;

import com.logistic.logistic.domain.Order;
import com.logistic.logistic.features.order.dto.OrderRequest;
import com.logistic.logistic.features.order.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toOrder(OrderRequest orderRequest);
    OrderResponse fromOder(Order order);
    List<OrderResponse> fromOrders(List<Order> orders);

}
