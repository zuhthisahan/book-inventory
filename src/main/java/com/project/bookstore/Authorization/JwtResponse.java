package com.project.bookstore.Authorization;


import com.project.bookstore.User.model.UsersEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Optional<UsersEntity> user;

    public JwtResponse(String accessToken, Optional<UsersEntity> usersEntity) {
        this.token = accessToken;
        this.user =usersEntity;
    }
}