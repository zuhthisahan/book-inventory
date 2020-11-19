package com.project.bookstore.ShoppingCart.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ShoppingCartDTO {
    private int customerId, bookId;
}
