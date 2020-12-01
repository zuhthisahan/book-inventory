package com.project.bookstore.Login;
import com.project.bookstore.Authorization.JwtProvider;
import com.project.bookstore.Authorization.JwtResponse;
import com.project.bookstore.Authorization.UserPrinciple;
import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Login.model.LoginDTO;
import com.project.bookstore.User.model.UsersEntity;
import com.project.bookstore.User.model.UsersEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class LoginController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationManager authenticationManager1;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private UsersEntityRepository usersEntityRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        System.out.println("hi");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPass()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Optional<UsersEntity> usersEntity = usersEntityRepository.findByUsername(userPrinciple.getUsername());

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt,usersEntity));
    }





}
