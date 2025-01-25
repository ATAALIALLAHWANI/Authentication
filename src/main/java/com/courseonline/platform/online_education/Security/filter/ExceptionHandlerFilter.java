package com.courseonline.platform.online_education.Security.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.courseonline.platform.online_education.Exception.UserNotFoundException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UserNotFoundException e) { //Feel free to create a separate function.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Email doesn't exist");
            response.getWriter().flush();
        }catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("JWT NOT VALID");
            response.getWriter().flush();
        }
        
        catch (RuntimeException e) {
            
            e.printStackTrace(); // Log exception for debugging
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("BAD REQUEST");
            response.getWriter().flush();         
        }  
    }
}
