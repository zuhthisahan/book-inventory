package com.project.bookstore.Transaction.model;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.ShoppingCart.model.ShoppingCartEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Car number is required")
    private int cardNo;

    @ManyToOne
    @JoinColumn(name = "customer_id ")
    private CustomerEntity customerEntity;




}
