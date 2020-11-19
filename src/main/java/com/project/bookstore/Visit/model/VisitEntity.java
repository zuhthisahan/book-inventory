package com.project.bookstore.Visit.model;


import com.project.bookstore.Book.model.BookEntity;
import com.project.bookstore.Customer.model.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit")
@Getter
@Setter
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;


    @NotNull
    private LocalDateTime checkedIn;

    private LocalDateTime checkedOut;


}
