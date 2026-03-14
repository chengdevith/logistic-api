package com.logistic.logistic.features.product;

import com.logistic.logistic.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
