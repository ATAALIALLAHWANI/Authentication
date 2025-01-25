package com.courseonline.platform.online_education.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;

import com.courseonline.platform.online_education.Security.Manager.CustomAuthenticationManager;
import com.courseonline.platform.online_education.Security.filter.AuthenticationFilter;
import com.courseonline.platform.online_education.Security.filter.CustomAccessDeniedHandler;
import com.courseonline.platform.online_education.Security.filter.ExceptionHandlerFilter;
import com.courseonline.platform.online_education.Security.filter.JWTAuthorizationFilter;

import lombok.AllArgsConstructor;
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_PATH);

        // CORS Configuration - Customize as needed
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*"); // Adjust as needed for your environment
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");

       http
    .csrf(csrf -> csrf.disable())
    .cors(cors -> cors.configurationSource(request -> corsConfig))
    .authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
    .exceptionHandling(exception -> exception
        .accessDeniedHandler(new CustomAccessDeniedHandler())) // Set custom access denied handler
    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
    .addFilter(authenticationFilter)
    .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
