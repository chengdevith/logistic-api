package com.logistic.logistic.features.inventory;

import com.logistic.logistic.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
