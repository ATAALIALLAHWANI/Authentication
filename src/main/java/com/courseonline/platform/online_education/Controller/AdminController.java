package com.courseonline.platform.online_education.Controller;

import com.courseonline.platform.online_education.InterfaceALL.UserServiceIntrface;
import com.courseonline.platform.online_education.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final UserServiceIntrface userService;


    // Delete user by email (Only accessible to admins)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete-user/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Get all users (Only accessible to admins)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all-users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    
    
}
