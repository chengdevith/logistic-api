package com.logistic.logistic.features.customer;

import com.logistic.logistic.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomersByEmail(String email);
}
