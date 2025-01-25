package com.courseonline.platform.online_education.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.courseonline.platform.online_education.InterfaceALL.UserServiceIntrface;
import com.courseonline.platform.online_education.Model.User;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/users") // Base URL for all user-related endpoints
public class UserController {
    
    @Autowired
    private final UserServiceIntrface userService;
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    
    
  
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // @PostMapping("/authenticate")
    // public ResponseEntity<User> authenticate(@RequestBody User user) {
    //     System.out.println("done done done ");
    //     return ResponseEntity.status(HttpStatus.CREATED).body(null);
    // }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }



    
}
