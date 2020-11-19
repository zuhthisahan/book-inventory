package com.project.bookstore.Transaction;

import com.project.bookstore.Transaction.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
@ResponseBody
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PreAuthorize("hasRole('User') or hasRole('Admin')")
    @PostMapping("/customer/transaction/{id}")
    public ResponseEntity<Object> AddToTransaction(@PathVariable("id") int id, @RequestBody TransactionDTO transactionDTO) throws IOException {
        System.out.println("coming......");
        Boolean out = transactionService.AddToTransaction(id,transactionDTO);
        if(out)return ResponseEntity.ok("<Transaction completed successfully");
        else return ResponseEntity.notFound().build();
    }
}
