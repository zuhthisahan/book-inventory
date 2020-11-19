package com.project.bookstore.Visit;

import com.project.bookstore.Visit.model.VisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@ResponseBody
public class VisitController {


    @Autowired
    private VisitService visitService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/customer/check-out/{id}")
    public ResponseEntity<Object> guestCheckOut(@PathVariable("id") int id){
        Boolean out = visitService.guestCheckOut(id);
        if(out)return ResponseEntity.ok(null);
        else return ResponseEntity.notFound().build();
    }

}
