package com.logistic.logistic.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Integer id;

    private LocalDateTime shipmentDate;

    @NotBlank(message = "Carrier is required")
    private String carrier;

    private String trackingNumber;

    private String status;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
