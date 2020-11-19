package com.project.bookstore.Discount.model;

import com.project.bookstore.Book.model.BookEntity;
import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Member.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountEntityRepository extends JpaRepository<DiscountEntity,Integer> {

}
