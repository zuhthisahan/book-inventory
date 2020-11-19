package com.project.bookstore.Member.model;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Discount.model.DiscountEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;




}
