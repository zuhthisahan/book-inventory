package com.project.bookstore.DiscountIdentificationMap.model;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Discount.model.DiscountEntity;
import com.project.bookstore.Member.model.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "discount_identification_map")
@Getter
@Setter
public class DiscountIdentificationMapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountEntity discountEntity;

}
