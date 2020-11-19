package com.project.bookstore.ShoppingCart;

import com.project.bookstore.ShoppingCart.model.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@ResponseBody
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    @PostMapping("/shoppingCart")
    public ResponseEntity<Object> getAllBook(@RequestBody ShoppingCartDTO shoppingCartDTO) {


        String res = (shoppingCartService.addToShoppingCart( shoppingCartDTO));
        if(res.equals("ok")) return ResponseEntity.ok("< Successfully Added to shopping cart >");
        else if (res.equals("book not found")) return ResponseEntity.notFound().build();
        else return ResponseEntity.badRequest().build();
    }

}
