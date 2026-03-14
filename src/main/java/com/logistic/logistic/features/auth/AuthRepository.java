package com.logistic.logistic.features.auth;

import com.logistic.logistic.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Customer, Integer> {
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
}
