package com.project.bookstore.User;

import com.project.bookstore.Customer.model.CustomerEntity;
import com.project.bookstore.Customer.model.CustomerEntityRepository;
import com.project.bookstore.Discount.EmailSenderService;
import com.project.bookstore.Discount.model.DiscountCreateDTO;
import com.project.bookstore.Discount.model.DiscountEntity;
import com.project.bookstore.Discount.model.DiscountEntityRepository;
import com.project.bookstore.DiscountIdentificationMap.model.DiscountIdentificationMapEntity;
import com.project.bookstore.DiscountIdentificationMap.model.DiscountIdentificationMapRepository;
import com.project.bookstore.Member.model.MemberEntity;
import com.project.bookstore.Member.model.MemberEntityRepository;
import com.project.bookstore.User.model.UserRegistrationDTO;
import com.project.bookstore.User.model.UsersEntity;
import com.project.bookstore.User.model.UsersEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserService {



    @Autowired
    private UsersEntityRepository usersEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private DiscountEntityRepository discountEntityRepository;

    @Autowired
    private MemberEntityRepository memberEntityRepository;

    @Autowired
    private DiscountIdentificationMapRepository discountIdentificationMapRepository;

    @Autowired
    private EmailSenderService service;




    public boolean addGuest(UserRegistrationDTO userRegistrationDTO) {
        if (usersEntityRepository.findByUsername(userRegistrationDTO.getUsername()).isPresent())
            return false;
        else {




                UsersEntity usersEntity = new UsersEntity();
                usersEntity.setUsername(userRegistrationDTO.getUsername());
                String pass = BCrypt.hashpw(userRegistrationDTO.getPassword(), BCrypt.gensalt());
                System.out.println(pass);
                usersEntity.setPassword(pass);
                usersEntity.setRole("User");

//                System.out.println("Postal" + userRegistrationDTO.getPostalAddress());
//                System.out.println("cardNO" + userRegistrationDTO.getCardNo());
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setUsername(userRegistrationDTO.getUsername());
                customerEntity.setPassword(pass);
                customerEntity.setPostalAddress(userRegistrationDTO.getPostalAddress());
                customerEntity.setRole("User");
                customerEntity.setCreatedAt(LocalDateTime.now());

                customerEntity.setEmail(userRegistrationDTO.getEmail());


                customerEntityRepository.save(customerEntity);

                usersEntity.setCustomerEntity((customerEntity));
                usersEntityRepository.save(usersEntity);



            return true;
        }
    }



    public boolean createDiscount(DiscountCreateDTO discountCreateDTO){

        System.out.println("coming....1");
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setType(discountCreateDTO.getType());
        discountEntity.setValid(true);
        discountEntity.setDiscount(discountCreateDTO.getDiscount());
        LocalDateTime today =  LocalDateTime.now();
        LocalDateTime expire = today.plusDays(discountCreateDTO.getDay());
        discountEntity.setExpireDate(expire);
        discountEntityRepository.save(discountEntity);

        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for (MemberEntity object : memberEntityList){
            System.out.println("coming2......");
            DiscountIdentificationMapEntity discountIdentificationMapEntity = new DiscountIdentificationMapEntity();
            discountIdentificationMapEntity.setMemberEntity(memberEntityRepository.getOne(object.getId()));
            discountIdentificationMapEntity.setDiscountEntity(discountEntity);


            //Generate promoCode
            String possibleLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_$";
            StringBuilder sb = new StringBuilder(3);
            for (int i = 0; i < 3; i++)
                sb.append(possibleLetters.charAt(new Random().nextInt(possibleLetters.length())));
            String code = sb.toString();

            discountIdentificationMapEntity.setCode(code);
            discountIdentificationMapRepository.save(discountIdentificationMapEntity);


//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setTo(object.getCustomerEntity().getEmail());
//
//            msg.setSubject("New Promotion Available");
//            msg.setText("We've sent you the new promotion. Thank you");
//
//           // javaMailSender.send(msg);

            // Send email to all member
            service.sendMail(object.getCustomerEntity().getEmail(),"Hello!! New discount created..","Discount created");


        }
        return true;
    }
}
