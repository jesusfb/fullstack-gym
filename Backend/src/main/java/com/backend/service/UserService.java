package com.backend.service;

import com.backend.model.User;
import com.backend.request.UserUpdateRequest;

import java.util.List;

public interface UserService
{
    User changePlantoUser(int user_id,int plan_id);
    List<User> getAllUsersByPlanId(int plan_id);
    List<User> getAllUsers();
    User getUserById(int id);
    User updateUser(UserUpdateRequest user, int id);
    void deleteUser(int id);
}
