package com.project.bookstore.ShoppingCart;


import com.project.bookstore.Book.model.BookEntity;
import com.project.bookstore.Book.model.BookEntityRepository;
import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Customer.model.CustomerEntityRepository;
import com.project.bookstore.ShoppingCart.model.ShoppingCartDTO;
import com.project.bookstore.ShoppingCart.model.ShoppingCartEntity;
import com.project.bookstore.ShoppingCart.model.ShoppingCartEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartEntityRepository shoppingCartEntityRepository;

    @Autowired
    private BookEntityRepository bookEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    public String addToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        CustomerEntity customerEntity = customerEntityRepository.getOne(shoppingCartDTO.getCustomerId());
        BookEntity bookEntity = bookEntityRepository.getOne(shoppingCartDTO.getBookId());
        if (customerEntity != null) {
            if (bookEntity != null) {
                ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
                shoppingCartEntity.setCustomerEntity(customerEntity);
                shoppingCartEntity.setBookEntity(bookEntity);
                shoppingCartEntityRepository.save(shoppingCartEntity);
                return "ok";


            } else {
                return "book not found";
            }

        } else {
            return "error";
        }
    }


}
