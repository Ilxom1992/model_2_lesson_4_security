package com.example.demo.controller;

import com.example.demo.payload.LoginDto;
import com.example.demo.security.JwtPrivider;
import com.example.demo.service.MyAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final MyAuthService myAuthService;
    final JwtPrivider jwtPrivider;

    public AuthController(MyAuthService myAuthService, JwtPrivider jwtPrivider) {
        this.myAuthService = myAuthService;
        this.jwtPrivider = jwtPrivider;
    }
    @PostMapping
    public HttpEntity<?> getAuth(@RequestBody LoginDto loginDto){
        UserDetails userDetails = myAuthService.loadUserByUsername(loginDto.getUserName());
        boolean existUser = userDetails.getPassword().equals(loginDto.getPassword());
        if (existUser){
            String token = jwtPrivider.generateToken(loginDto.getUserName());
            return ResponseEntity.ok(token);
        }
return ResponseEntity.status(401).body("Login yoki parol hato");
    }



}
