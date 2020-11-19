package com.project.bookstore.ShoppingCart.model;

import com.project.bookstore.Book.model.BookEntity;
import com.project.bookstore.Customer.model.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id ")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "book_id ")
    private BookEntity bookEntity;

}
