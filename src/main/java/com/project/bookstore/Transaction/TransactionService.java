package com.project.bookstore.Transaction;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Customer.model.CustomerEntityRepository;
import com.project.bookstore.Discount.model.DiscountEntity;
import com.project.bookstore.Discount.model.DiscountEntityRepository;
import com.project.bookstore.DiscountIdentificationMap.model.DiscountIdentificationMapEntity;
import com.project.bookstore.DiscountIdentificationMap.model.DiscountIdentificationMapRepository;
import com.project.bookstore.Logs.model.LogsEntity;
import com.project.bookstore.Logs.model.LogsRepository;
import com.project.bookstore.Member.model.MemberEntity;
import com.project.bookstore.Member.model.MemberEntityRepository;
import com.project.bookstore.ShoppingCart.model.ShoppingCartEntity;
import com.project.bookstore.ShoppingCart.model.ShoppingCartEntityRepository;
import com.project.bookstore.Transaction.model.TransactionDTO;
import com.project.bookstore.Transaction.model.TransactionEntity;

import com.project.bookstore.Transaction.model.TransactionEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;



@Service
public class TransactionService {

    @Autowired
    private ShoppingCartEntityRepository shoppingCartEntityRepository;

    @Autowired
    private TransactionEntityRepository transactionEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;
    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private DiscountEntityRepository discountEntityRepository;

    @Autowired
    private DiscountIdentificationMapRepository discountIdentificationMapRepository;

    @Autowired
    private MemberEntityRepository memberEntityRepository;

    public boolean AddToTransaction(int id, TransactionDTO transactionDTO) throws IOException {
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(id);
        List<ShoppingCartEntity> shoppingCartEntity = (shoppingCartEntityRepository.findByCustomerEntity(customerEntity.get()));



        Optional<MemberEntity> memberEntity = memberEntityRepository.findByCustomerEntity(customerEntity.get());
        if (shoppingCartEntity != null) {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setCustomerEntity(customerEntity.get());
            transactionEntity.setCardNo(transactionDTO.getCardNo());
            transactionEntityRepository.save(transactionEntity);

            double total =0;
            for(ShoppingCartEntity object: shoppingCartEntity){
                total = total + object.getBookEntity().getPrice();
            }
            if(memberEntity.isPresent()) {
                DiscountIdentificationMapEntity identificationMapEntity = discountIdentificationMapRepository.getOne(memberEntity.get().getId());
                total = total * (identificationMapEntity.getDiscountEntity().getDiscount());
            }


       String info = ("Customer Name : " +customerEntity.get().getUsername() + "Total Amount : " + total);
         LogsEntity logsEntity = new LogsEntity();
        logsEntity.setInfo(info);
        logsRepository.save(logsEntity);

            return true;
        } else {
            return false;
        }

    }
}

