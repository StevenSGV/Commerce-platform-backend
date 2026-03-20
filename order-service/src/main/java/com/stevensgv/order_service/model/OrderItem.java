package com.stevensgv.order_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @NotNull(message = "Product ID is required.")
    @Positive(message = "Negative numbers are not allowed.")
    private Long productId;

    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be a positive number.")
    private Integer quantity;

    @NotNull(message = "Unit price is required.")
    @Positive(message = "Negative numbers are not allowed.")
    private BigDecimal unitPrice;
}
