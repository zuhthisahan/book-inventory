package com.project.bookstore.Book.model;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity,Integer> {
    BookEntity findByTitle(String name);




}
