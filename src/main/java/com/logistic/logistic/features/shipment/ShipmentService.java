package com.logistic.logistic.features.shipment;

import com.logistic.logistic.features.shipment.dto.ShipmentRequest;
import com.logistic.logistic.features.shipment.dto.ShipmentResponse;

import java.util.List;

public interface ShipmentService {
    List<ShipmentResponse> getAllShipment();
    ShipmentResponse getShipmentById(Integer id);
    ShipmentResponse createShipment(ShipmentRequest shipmentRequest);
}
