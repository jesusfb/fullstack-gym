package com.backend.service;

import com.backend.model.User;

import java.util.List;

public interface UserService
{
    List<User> getAllUsersByPlanId(int plan_id);
    User saveUser (int plan_id,User userRequest);
    User getUserById(int id);
    User updateUser(User user,int id);
    void deleteUser(int id);
}
