package com.project.bookstore.Book;

import com.project.bookstore.Book.model.BookEditDTO;
import com.project.bookstore.User.model.UsersEntityRepository;
import com.project.bookstore.Book.model.BookEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1")
@ResponseBody
public class BookController {


    @Autowired
    private BookEntityRepository bookEntityRepository;

    @Autowired
    private UsersEntityRepository usersEntityRepository;

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    @GetMapping("/books")
    public ResponseEntity<Object> getAllBook() {

        return ResponseEntity.ok(bookService.getAllBook());
    }

    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    @GetMapping("/book")
    public ResponseEntity<Object> getBook(@RequestParam("q") String name) {
        return ResponseEntity.ok(bookService.getBook(name));
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/book/quantity")
    public ResponseEntity<Object> getABookQuantity(@RequestParam("q") String name) {
        return ResponseEntity.ok(bookService.findABookQuantity(name));
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/book/stop-order/{name}")
    public ResponseEntity<Object> getABookStopOrder(@PathVariable("name")  String name) {
        String res = bookService.getStopOrder(name);
        if(res.equals("true"))return ResponseEntity.ok().body("< Need to re-order the book >");
        else if(res.equals("false")) return ResponseEntity.ok().body("< No need to re-order now");
        else return ResponseEntity.badRequest().build();

    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/book/{name}")
    public ResponseEntity<Object> guestCheckInEdit(@PathVariable("name") String name, @RequestBody BookEditDTO bookEditDTO){

        boolean out = bookService.bookPriceEdit(name,bookEditDTO);
        if(out)return ResponseEntity.ok("<Price Changed>");
        else return ResponseEntity.notFound().build();
    }


}
