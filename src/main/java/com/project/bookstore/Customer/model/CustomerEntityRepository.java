package com.project.bookstore.Customer.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity,Integer> {
    Optional<CustomerEntity> findByUsername(String username);
}
