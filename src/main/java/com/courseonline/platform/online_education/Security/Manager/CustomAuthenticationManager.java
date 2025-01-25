package com.courseonline.platform.online_education.Security.Manager;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.courseonline.platform.online_education.Model.User;
import com.courseonline.platform.online_education.Service.UserService;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserService userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userServiceImpl.getUserByEmail(authentication.getName());
        
        // Check if the password matches
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (String role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));  // e.g., "ROLE_USER", "ROLE_ADMIN"
            }
        }

        
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), authorities);
    }
}
