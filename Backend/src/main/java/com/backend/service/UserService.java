package com.backend.service;

import com.backend.model.Instructor;
import com.backend.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService
{
    User changePlantoUser(int user_id,int plan_id);
    List<User> getAllUsersByPlanId(int plan_id);
    List<User> getAllUsers();
    User saveUser (int plan_id,User userRequest);
    User getUserById(int id);
    User updateUser(User user,int id);
    void deleteUser(int id);
    User uploadImage(MultipartFile file, int id) throws IOException;

    User deleteImage(int id);
}
