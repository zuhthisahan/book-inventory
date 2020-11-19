package com.project.bookstore.ShoppingCart.model;

import com.project.bookstore.Book.model.BookEntity;
import com.project.bookstore.Customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartEntityRepository extends JpaRepository<ShoppingCartEntity,Integer> {
    List<ShoppingCartEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
