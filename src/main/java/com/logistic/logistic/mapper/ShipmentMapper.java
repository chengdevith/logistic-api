package com.logistic.logistic.mapper;

import com.logistic.logistic.domain.Shipment;
import com.logistic.logistic.features.shipment.dto.ShipmentRequest;
import com.logistic.logistic.features.shipment.dto.ShipmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    List<ShipmentResponse> fromShipments(List<Shipment> shipment);

    ShipmentResponse fromShipment(Shipment shipment);

    @Mapping(target = "order", ignore = true)
    Shipment toShipment(ShipmentRequest shipmentRequest);
}
