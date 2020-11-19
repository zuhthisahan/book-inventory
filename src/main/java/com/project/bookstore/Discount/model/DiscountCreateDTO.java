package com.project.bookstore.Discount.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiscountCreateDTO {
    private double discount;
    private String type;
    private int day;

}
