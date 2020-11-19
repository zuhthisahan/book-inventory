package com.project.bookstore.Member;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Customer.model.CustomerEntityRepository;
import com.project.bookstore.Login.model.LoginDTO;
import com.project.bookstore.Member.model.MemberEntity;
import com.project.bookstore.Member.model.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;


import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private MemberEntityRepository memberEntityRepository;






    public String addMember (LoginDTO loginDTO) throws NoSuchAlgorithmException {
        System.out.println();
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findByUsername(loginDTO.getUsername());
        System.out.println("Login");
        System.out.println(loginDTO.getUsername() + ' '  +loginDTO.getPass() );
        System.out.println("data");




        System.out.println(customerEntity.get().getUsername() + ' ' + customerEntity.get().getPassword());
        if(customerEntity.isPresent() && customerEntity.get().getUsername().equals(loginDTO.getUsername())  && BCrypt.checkpw(loginDTO.getPass(), customerEntity.get().getPassword())   ){
            System.out.println("coming in");
           if(memberEntityRepository.findByCustomerEntity(customerEntity.get()).isPresent()){

               System.out.println("going 1");
               return "Member Already Exist";
           }
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setCustomerEntity(customerEntity.get());
            memberEntityRepository.save(memberEntity);
            System.out.println("going 2");
                return "Successfully Registered";

    }else {
            return "error";
        }


    }
}
