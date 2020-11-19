package com.project.bookstore.Visit;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Customer.model.CustomerEntityRepository;
import com.project.bookstore.Visit.model.VisitEntity;
import com.project.bookstore.Visit.model.VisitEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitService {
    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private VisitEntityRepository visitEntityRepository;
    public void addToCheckIn(String name){
        Optional<CustomerEntity> customerEntity = customerEntityRepository.findByUsername(name);
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setCustomerEntity(customerEntity.get());
        visitEntity.setCheckedIn(LocalDateTime.now());
        visitEntityRepository.save(visitEntity);
    }

    public Boolean guestCheckOut(int id) {
        System.out.println("Sfnd,mdmd,d");
        System.out.println(visitEntityRepository.findById(id).get().getCheckedIn());
        VisitEntity visitEntity = visitEntityRepository.findByCheckedOutAndId(null, id);
        visitEntity.setCheckedOut(LocalDateTime.now());
        visitEntityRepository.save(visitEntity);
        return true;
    }
}
