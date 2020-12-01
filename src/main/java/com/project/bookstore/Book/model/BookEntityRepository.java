package com.project.bookstore.Book.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEntityRepository extends JpaRepository<BookEntity,Integer> {
    BookEntity findByTitle(String name);




}

