package com.courseonline.platform.online_education.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data  // Lombok annotation to generate getters, setters, and other methods
@Document(collection = "users")  
public class User {

    @Id  // MongoDB primary key
    private String id;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Positive(message = "Date must be a positive number")
    private int date;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    private String phone;

    @NotBlank(message = "University name is mandatory")
    private String university;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    private String createdAt ;
     private List<String> roles;

    public User(String id, String username, int date, String email, String phone, String university, String password, List<String> roles) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.university = university;
        this.password = password;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.roles = roles; // Add roles to constructor
    }
}
