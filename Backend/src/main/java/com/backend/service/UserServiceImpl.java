package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.*;
import com.backend.repository.ImageRepository;
import com.backend.repository.PlanRepository;
import com.backend.repository.UserRepository;
import com.backend.tool.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public User changePlantoUser(int user_id, int plan_id)
    {
        User user = planRepository.findById(plan_id).map(
                plan -> {
                    User existingUser = userRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User","Id",user_id));
                    existingUser.setPlan(plan);
                    return userRepository.save(existingUser);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Plan","Id",plan_id));
        return user;
    }

    @Override
    public List<User> getAllUsersByPlanId(int plan_id)
    {
        if (!planRepository.existsById(plan_id))
        {
            throw new ResourceNotFoundException("Plan","Id",plan_id);
        }

        List<User> users = userRepository.findByPlan_Id(plan_id);
        return users;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(int plan_id, User userRequest)
    {
        User user = planRepository.findById(plan_id).map( plan ->
                {
                    userRequest.setPlan(plan);
                    return userRepository.save(userRequest);
                }
        ).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",plan_id));
        return user;
    }

    @Override
    public User getUserById(int id)
    {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        return existingUser;
    }

    @Override
    public User updateUser(User user, int id)
    {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        existingUser.setUser_name(user.getUser_name());
        existingUser.setUser_email(user.getUser_email());
        existingUser.setUser_address(user.getUser_address());
        existingUser.setUser_type(user.getUser_type());
        existingUser.setUser_lastname(user.getUser_lastname());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(int id)
    {
        userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        userRepository.deleteById(id);
    }

    @Override
    public User uploadImage(MultipartFile file, int id) throws IOException
    {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        if(!existingUser.getImage_url().isEmpty())
        {
            existingUser = deleteImage(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingUser.setImage_url(file.getOriginalFilename());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public User deleteImage(int id) {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        String tobedeletedImageName = existingUser.getImage_url();
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has not been found"));
        imageRepository.delete(tobedeletedImage);
        existingUser.setImage_url("");
        return userRepository.save(existingUser);
    }
}