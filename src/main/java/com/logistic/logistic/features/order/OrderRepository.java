package com.logistic.logistic.features.order;

import com.logistic.logistic.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
