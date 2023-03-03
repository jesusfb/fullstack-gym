package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.*;
import com.backend.repository.CourseRepository;
import com.backend.repository.PlanRepository;
import com.backend.repository.UserRepository;
import com.backend.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PlanRepository planRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User changePlantoUser(int user_id, int plan_id)
    {
        User user = planRepository.findById(plan_id).map(
                plan -> {
                    User existingUser = userRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User","Id",user_id));
                    existingUser.setPlan(plan);
                    LocalDate startPlan = LocalDate.now();
                    existingUser.setRegistered_date(startPlan);
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
        users.forEach(user -> {
            user.setPlan_id(user.getPlan().getId());
        });
        return users;
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setPlan_id(user.getPlan().getId());
        });
        return users;
    }

    @Override
    public User getUserById(int id)
    {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        existingUser.setPlan_id(existingUser.getPlan().getId());
        return existingUser;
    }

    @Override
    public User updateUser(UserUpdateRequest user, int id)
    {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        existingUser.setUser_name(user.getUser_name());
        existingUser.setEmail(user.getEmail());
        existingUser.setUser_address(user.getUser_address());
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
}