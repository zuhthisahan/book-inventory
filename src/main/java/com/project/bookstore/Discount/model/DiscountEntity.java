package com.project.bookstore.Discount.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount")
@Getter
@Setter
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Discount Type is required")
    private String type;

    @NotEmpty(message = "discount value is required")
    @Column(name = "promotion")
    private double discount;

    @NotEmpty(message = "Expire date is required")
    private LocalDateTime expireDate;
    @NotEmpty
    private Boolean valid;



}
