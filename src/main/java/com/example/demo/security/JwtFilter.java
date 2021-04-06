package com.example.demo.security;

import com.example.demo.service.MyAuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    final JwtPrivider jwtPrivider;
    final MyAuthService myAuthService;

    public JwtFilter(JwtPrivider jwtPrivider, MyAuthService myAuthService) {
        this.jwtPrivider = jwtPrivider;
        this.myAuthService = myAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,

                                    FilterChain filterChain) throws ServletException, IOException {
        //REQUESTDAN TOKENNI OLISH
        String token = httpServletRequest.getHeader("Authorization");
        //TOKENNI BOSHLANISH BEARER BO'LISHNI TEKSHIRYAYABMIZ
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
            //TOKENNI VAIDATSIYADAN O'TKAZDIK (TOKIN BUTLIGI TEKSHIRILADI)
            boolean validateToken = jwtPrivider.validateToken(token);
            if (validateToken) {
             //TOKENNI ICHIDAN USENAMENI OLDIK
                String userNameFromToken = jwtPrivider.getUserNameFromToken(token);
                //USENAME ORQALI USER DETAILESNI OLDIK
                UserDetails userDetails = myAuthService.loadUserByUsername(userNameFromToken);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                //SISTEMAGA KIM KIRGANINI O'RNATDIK
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}