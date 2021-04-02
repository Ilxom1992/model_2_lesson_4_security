package com.example.demo.controller;

import com.example.demo.payload.LoginDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping
    public HttpEntity<?> getAuth(@RequestBody LoginDto loginDto){
return ResponseEntity.ok("ok");

    }



}
