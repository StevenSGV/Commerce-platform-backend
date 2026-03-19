package com.stevensgv.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID is required.")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotBlank(message = "Status is required.")
    private String status;

    @NotNull(message = "Total is required.")
    @Positive(message = "Total must be a positive number.")
    private BigDecimal total;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> orderItems;
}
