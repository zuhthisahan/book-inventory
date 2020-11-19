package com.project.bookstore.User.model;

import com.project.bookstore.User.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersEntityRepository  extends JpaRepository<UsersEntity,Integer> {
    Optional<UsersEntity> findByUsername(String username);

}
