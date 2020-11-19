package com.project.bookstore.Book.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "title is Required")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "author name is Required")
    @Column(name = "author")
    private String author;

    @NotEmpty(message = "price is Required")
    @Column(name = "price")
    private double price;


    @Column(name = "count")
    private int count ;

    @NotEmpty(message = "stop order is Required")
    @Column(name = "stop_order")
    private boolean stopOrder;



}
