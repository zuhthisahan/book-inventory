package com.project.bookstore.User;

import com.project.bookstore.Discount.model.DiscountCreateDTO;
import com.project.bookstore.Logs.model.LogsRepository;
import com.project.bookstore.User.model.UserRegistrationDTO;
import com.project.bookstore.User.model.UsersEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private UsersEntityRepository usersEntityRepository;

    @PostMapping("/registration")
    public ResponseEntity<Object> addGuest(@RequestBody UserRegistrationDTO userRegistrationDTO) throws Exception {
        System.out.println( "coming");
        boolean out = userService.addGuest(userRegistrationDTO);
        if (out) return ResponseEntity.ok("< Guest Record >");
        return ResponseEntity.badRequest().body("Guest Exists");
    }


    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/discount")
    public ResponseEntity<Object> createDiscount(@RequestBody DiscountCreateDTO discountCreateDTO){
        System.out.println("coming initial");
        boolean out = userService.createDiscount(discountCreateDTO);
        if (out) return ResponseEntity.ok("< Promotion Created Successfully >");
        return ResponseEntity.badRequest().build();
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/logs")
    public ResponseEntity<Object>viewLogs(){
        return ResponseEntity.ok(logsRepository.findAll());
    }

    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    @GetMapping("/user/{name}")
    public ResponseEntity<Object> getCustomer(@PathVariable("name") String name) throws Exception {
        return ResponseEntity.ok(usersEntityRepository.findByUsername(name));
    }
}
