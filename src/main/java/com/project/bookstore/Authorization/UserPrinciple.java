package com.project.bookstore.Authorization;

import com.fasterxml.jackson.annotation.JsonIgnore;


import com.project.bookstore.User.model.UsersEntity;
import com.project.bookstore.Customer.model.CustomerEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;



import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;
    private int id;

    private CustomerEntity customerEntity;
    private String username;

    @JsonIgnore
    private String password;




    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(int id, String username, String password, CustomerEntity customerEntity, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.customerEntity=customerEntity;
        this.authorities = authorities;


    }

    public int getId() {
        return id;
    }




    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public CustomerEntity getCustomerEntity(){return customerEntity;}
    public static UserPrinciple build(UsersEntity user) {
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+user.getRole());



        return new UserPrinciple(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getCustomerEntity(),
                authorities
        );
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

}