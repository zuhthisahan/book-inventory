package com.project.bookstore.User.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String username, password, postalAddress, role,email;
    private int cardNo;


}
