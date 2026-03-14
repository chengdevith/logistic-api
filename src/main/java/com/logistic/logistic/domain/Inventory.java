package com.logistic.logistic.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantityAvailable;

    private String location;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
