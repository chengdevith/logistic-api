package com.logistic.logistic.features.shipment;

import com.logistic.logistic.features.shipment.dto.ShipmentRequest;
import com.logistic.logistic.features.shipment.dto.ShipmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;

    @GetMapping
    List<ShipmentResponse> findAllShipment(){
        return shipmentService.getAllShipment();
    }

    @GetMapping("/{id}")
    ShipmentResponse findShipmentById(@PathVariable Integer id){
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    ShipmentResponse createShipment(@Valid @RequestBody ShipmentRequest shipmentRequest){
        return shipmentService.createShipment(shipmentRequest);
    }
}
