package com.project.bookstore.User.model;

import com.project.bookstore.Customer.model.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Optional;


@Entity
@Table(name = "users")
@Getter
@Setter
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username,password,role;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity  customerEntity;



}
