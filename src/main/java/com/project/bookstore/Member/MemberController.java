package com.project.bookstore.Member;


import com.project.bookstore.Login.model.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1")
@ResponseBody
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PreAuthorize("hasRole('Admin') or hasRole('User')")
    @PostMapping("/member")
    public ResponseEntity<?> addMember(@Valid @RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException {
        String out = memberService.addMember(loginDTO);

        if(out.equals("error"))return ResponseEntity.badRequest().body("<< Customer Authentication Failed >>");
        else if (out.equals("Member Already Exist")) return ResponseEntity.badRequest().body("<< Member Already Exist >>");
        else return ResponseEntity.ok("< Guest Record >");

//        if (out) return ResponseEntity.ok("< Guest Record >");
//        return ResponseEntity.badRequest().body("Customer Authentication Failed");
    }

}
