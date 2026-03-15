package com.logistic.logistic.features.order;

import com.logistic.logistic.domain.*;
import com.logistic.logistic.features.customer.CustomerRepository;
import com.logistic.logistic.features.order.dto.OrderRequest;
import com.logistic.logistic.features.order.dto.OrderResponse;
import com.logistic.logistic.features.product.ProductRepository;
import com.logistic.logistic.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderMapper.fromOrders(orderRepository.findAll());
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        return orderMapper.fromOder(orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + id + " not found")
                ));
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer " + orderRequest.customerId() + " not found"));
        Order order = orderMapper.toOrder(orderRequest);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setCustomer(customer);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> orderItems = orderRequest.orderItems().stream().map(itemRequest -> {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + itemRequest.productId() + " not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setPrice(itemRequest.price());
            orderItem.setProduct(product);
            orderItem.setOrder(savedOrder);
            return orderItem;
        }).toList();

        orderItemRepository.saveAll(orderItems);

        savedOrder.setOrderItems(orderItems);
        return orderMapper.fromOder(order);
    }
}
