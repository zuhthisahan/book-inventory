package com.project.bookstore.Member.model;

import com.project.bookstore.Customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberEntityRepository  extends JpaRepository<MemberEntity,Integer> {
    Optional<MemberEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
