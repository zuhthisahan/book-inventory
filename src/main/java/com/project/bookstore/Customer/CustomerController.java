package com.project.bookstore.Customer;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Customer.model.CustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
@ResponseBody
public class CustomerController {

    @Autowired
    private CustomerEntityRepository customerEntityRepository;


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/customer")
    public ResponseEntity<Object> getCustomers() throws Exception {
        return ResponseEntity.ok(customerEntityRepository.findAll());
    }



}
