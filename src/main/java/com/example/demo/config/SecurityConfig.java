package com.example.demo.config;

import com.example.demo.security.JwtFilter;
import com.example.demo.service.MyAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final MyAuthService myAuthService;
    final JwtFilter jwtFilter;

    public SecurityConfig(MyAuthService myAuthService, JwtFilter jwtFilter) {
        this.myAuthService = myAuthService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(myAuthService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/api/auth").permitAll()
                .anyRequest().authenticated();
        //SPRING SECURITYGA UsernamePasswordAuthenticationFilter.class DAN OLDIN JWT FILTER ISHLASHINI AYTAYABDI
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //SPRING SECURITYGA USHLAB QOLNMMASLIGINI BUYURMOQDA
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }
@Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
