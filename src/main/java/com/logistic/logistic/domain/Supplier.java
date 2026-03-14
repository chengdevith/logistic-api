package com.logistic.logistic.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer id;

    @NotBlank(message = "Supplier name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Contact person is required")
    @Column(name = "contact_person")
    private String contactPerson;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String address;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
