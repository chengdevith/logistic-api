package com.logistic.logistic.features.shipment;

import com.logistic.logistic.domain.Order;
import com.logistic.logistic.domain.Shipment;
import com.logistic.logistic.features.order.OrderRepository;
import com.logistic.logistic.features.shipment.dto.ShipmentRequest;
import com.logistic.logistic.features.shipment.dto.ShipmentResponse;
import com.logistic.logistic.mapper.ShipmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService{

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;
    private final OrderRepository orderRepository;

    @Override
    public List<ShipmentResponse> getAllShipment() {
        return shipmentMapper.fromShipments(shipmentRepository.findAll());
    }

    @Override
    public ShipmentResponse getShipmentById(Integer id) {
        return shipmentMapper.fromShipment(shipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Shipment" + id + "Not found")
                ));
    }

    @Override
    public ShipmentResponse createShipment(ShipmentRequest shipmentRequest) {
        Order order = orderRepository.findById(shipmentRequest.orderId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order" +shipmentRequest.orderId() + "Not found" ));
        Shipment shipment = shipmentMapper.toShipment(shipmentRequest);
        shipment.setShipmentDate(LocalDateTime.now());
        shipment.setOrder(order);
        return shipmentMapper.fromShipment(shipmentRepository.save(shipment));
    }
}
