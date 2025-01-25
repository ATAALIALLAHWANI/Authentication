package com.courseonline.platform.online_education.Service;

import com.courseonline.platform.online_education.Model.User;
import com.courseonline.platform.online_education.Repository.UserRepository;

import lombok.AllArgsConstructor;

import com.courseonline.platform.online_education.Exception.DuplicateEmailException;
import com.courseonline.platform.online_education.Exception.UserNotFoundException;
import com.courseonline.platform.online_education.InterfaceALL.UserServiceIntrface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService  implements UserServiceIntrface{

    @Autowired
    private final UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @Override
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    
    // Get user by email 
    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return unwrapUser(user, email);
    } 

    // Save user
    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException("Email is already in use.");
        }

         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
     @Override
    public void deleteUserByEmail(String email) {
        // Retrieve the user from the repository as Optional
        Optional<User> userOptional = userRepository.findByEmail(email);
    
        // If the user exists, delete it; otherwise, throw a UserNotFoundException
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(email, User.class));
        userRepository.delete(user);  // Delete the user from the repository
    }
    

    static User unwrapUser(Optional<User> entity, String id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id, User.class);
    }
    
}
