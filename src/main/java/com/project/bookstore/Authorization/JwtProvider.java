package com.project.bookstore.Authorization;

import com.project.bookstore.Visit.VisitService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Autowired
    private VisitService visitService;

    private String key = "random_secret_key";
    private  String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
    private byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Key);
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);



    public String generateJwtToken(Authentication authentication) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (30*60000));

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        Map<String,Object> map= new HashMap<>();
        map.put("role",String.valueOf(userPrincipal.getAuthorities().toArray()[0]));
        map.put("username",userPrincipal.getUsername());
//        System.out.println("coming000000......");
        if (userPrincipal.getCustomerEntity()!= null){
            map.put("customer_id",userPrincipal.getCustomerEntity().getId());
            if(userPrincipal.getCustomerEntity().getRole().contains("User")) {
//                System.out.println("coming2.......");
                visitService.addToCheckIn(userPrincipal.getUsername());
            }
        }






        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                 .setClaims(map)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretBytes)
                .compact();


    }


    public String getUserNameFromJwtToken(String token) {
        return String.valueOf(Jwts.parser()
                .setSigningKey(secretBytes)
                .parseClaimsJws(token)
                .getBody().get("username"));



    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretBytes).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }


}
