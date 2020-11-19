package com.project.bookstore.Transaction.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Integer> {
}
