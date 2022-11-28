package com.backend.service;

import com.backend.model.User;

import java.util.List;

public interface UserService
{
    User saveUser (User user);
    List<User> getAllUsers();
    User getUserById(int id);
    User updateUser(User user,int id);
    void deleteUser(int id);
}
