package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {




   @Autowired
   PasswordEncoder passwordEncoder;




    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<User> userList =new ArrayList<>(
                Arrays.asList ( new User("pdp",passwordEncoder.encode("pdp.uz"),new ArrayList<>()),
                        new User("pd",passwordEncoder.encode("pdp.uz"),new ArrayList<>()),
                        new User("p",passwordEncoder.encode("pdp.uz"),new ArrayList<>()),
                        new User("pdppp",passwordEncoder.encode("pdp.uz"),new ArrayList<>()))
        );

        for (User user:userList) {
            if (user.getUsername().equals(userName));
            return user;
        }

        throw  new UsernameNotFoundException("User Not found");


    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
