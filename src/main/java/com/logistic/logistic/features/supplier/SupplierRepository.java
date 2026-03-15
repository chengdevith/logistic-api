package com.logistic.logistic.features.supplier;

import com.logistic.logistic.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
