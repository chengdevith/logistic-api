package com.logistic.logistic.features.order;

import com.logistic.logistic.features.order.dto.OrderRequest;
import com.logistic.logistic.features.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    List<OrderResponse> findAllOrder(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    OrderResponse findOrderById(@PathVariable Integer id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
}
