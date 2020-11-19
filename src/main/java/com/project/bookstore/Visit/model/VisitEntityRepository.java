package com.project.bookstore.Visit.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitEntityRepository extends JpaRepository<VisitEntity,Integer> {
    VisitEntity findByCheckedOutAndId(String s,int id);
}
