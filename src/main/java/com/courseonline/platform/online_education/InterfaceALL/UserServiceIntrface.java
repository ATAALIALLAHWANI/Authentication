package com.courseonline.platform.online_education.InterfaceALL;

import java.util.List;

import com.courseonline.platform.online_education.Model.User;

public interface UserServiceIntrface {
 List<User> getAllUsers() ;
 public User getUserById(String id) ;
 public User getUserByEmail(String email);
 public User saveUser(User user);
 public void deleteUserByEmail(String Email);
}
