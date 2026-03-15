package com.logistic.logistic.features.shipment;

import com.logistic.logistic.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
}
