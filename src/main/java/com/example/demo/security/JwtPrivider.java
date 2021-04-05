package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
@Component
public class JwtPrivider {
    long expireTime=36_000_000;
static String sectret="Yangi parol login";
    public String generateToken(String userName){
String token= Jwts
        .builder()
        .setSubject(userName)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+expireTime))
        .signWith(SignatureAlgorithm.HS512,sectret)
        .compact();
return token;

}



}
