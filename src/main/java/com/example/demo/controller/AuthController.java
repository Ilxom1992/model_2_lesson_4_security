package com.example.demo.controller;

import com.example.demo.payload.LoginDto;
import com.example.demo.security.JwtPrivider;
import com.example.demo.service.MyAuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final MyAuthService myAuthService;
    final JwtPrivider jwtPrivider;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;

    public AuthController(MyAuthService myAuthService, JwtPrivider jwtPrivider, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.myAuthService = myAuthService;
        this.jwtPrivider = jwtPrivider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping
    public HttpEntity<?> getAuth(@RequestBody LoginDto loginDto) {
        //   UserDetails userDetails = myAuthService.loadUserByUsername(loginDto.getUserName());
        //   boolean matches =passwordEncoder.matches(loginDto.getPassword(),userDetails.getPassword());
        //    if (matches){
        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                    loginDto.getUserName(),
                    loginDto.getPassword()
            )));

            String token = jwtPrivider.generateToken(loginDto.getUserName());
            return ResponseEntity.ok(token);
        }
        catch (BadCredentialsException e){
            return ResponseEntity.status(401).body("Login yoki parol hato");
        }




    }



}
