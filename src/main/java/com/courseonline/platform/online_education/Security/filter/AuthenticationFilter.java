package com.courseonline.platform.online_education.Security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.courseonline.platform.online_education.Model.LoginRequest;
import com.courseonline.platform.online_education.Security.SecurityConstants;
import com.courseonline.platform.online_education.Security.Manager.CustomAuthenticationManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
     private CustomAuthenticationManager authenticationManager;
     @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
       
                try{
                    LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

                    // Create authentication token with email and password
                    Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        
                    // Log the email and password (optional)
                    System.out.println("Email: " + loginRequest.getEmail());
                    System.out.println("Password: " + loginRequest.getPassword());
        
                    return authenticationManager.authenticate(authentication);

                }catch(IOException e){
               throw new RuntimeException();
                }
    }

    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

   @Override
protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    // Generate a JWT token with the username (or email) as the subject
    String token = JWT.create()
    .withSubject(authResult.getName())
    .withClaim("roles", authResult.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList()))
    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
    .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

    // Add the token in the Authorization header
    response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
}



}
