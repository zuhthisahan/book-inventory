package com.project.bookstore.Book.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookGetDTO {

    private String title,author;
    private double price;
}
