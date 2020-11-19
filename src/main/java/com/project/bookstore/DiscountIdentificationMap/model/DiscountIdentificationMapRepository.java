package com.project.bookstore.DiscountIdentificationMap.model;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Discount.model.DiscountEntity;
import com.project.bookstore.Member.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountIdentificationMapRepository  extends JpaRepository<DiscountIdentificationMapEntity, Integer> {
    List <DiscountIdentificationMapEntity> findByMemberEntity(MemberEntity memberEntity);
}
