package com.project.bookstore.Book;

import com.project.bookstore.Book.model.BookEditDTO;
import com.project.bookstore.Book.model.BookEntity;
import com.project.bookstore.Book.model.BookEntityRepository;
import com.project.bookstore.Book.model.BookGetDTO;
import com.project.bookstore.User.model.UsersEntityRepository;
import com.project.bookstore.Util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookEntityRepository bookEntityRepository;

    @Autowired
    private UsersEntityRepository usersEntityRepository;

    public BookEntity getBook(String name) {
        BookEntity bookEntityList = bookEntityRepository.findByTitle(name);

        return bookEntityList;
    }


    public int findABookQuantity(String name){
        return getBook(name).getCount();
    }


    public List<BookGetDTO> getAllBook(){

        // Model mapper class to map the the Book entity to BookDTO and sort it by title in ascending order.
        List<BookGetDTO> listOfPostDTO = ObjectMapperUtils.mapAll(bookEntityRepository.findAll(Sort.by(Sort.Direction.ASC, "title")), BookGetDTO.class);

        return listOfPostDTO;


    }



    public String getStopOrder(String name){
        int threshold = 15;
        BookEntity bookEntity = bookEntityRepository.findByTitle(name);
        if(bookEntity.getCount()< threshold){
            bookEntity.setStopOrder(true);
            bookEntityRepository.save(bookEntity);
            return "true";
        }else {
            bookEntity.setStopOrder(false);
            bookEntityRepository.save(bookEntity);
            return "false";
        }
    }

    public boolean bookPriceEdit(String name, BookEditDTO bookEditDTO){
        BookEntity bookEntity = bookEntityRepository.findByTitle(name);
        if(bookEntity!= null){
            bookEntity.setPrice(bookEditDTO.getPrice());
            bookEntityRepository.save(bookEntity);
            return true;
        }
        return false;
    }



}
