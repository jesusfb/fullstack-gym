package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.User;
import com.backend.repository.PlanRepository;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanRepository planRepository;

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
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
        {
            return user.get();
        }
        else
        {
            throw new ResourceNotFoundException("User","Id",id);
        }
    }

    @Override
    public User updateUser(User user, int id)
    {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        existingUser.setUser_adt(user.getUser_adt());
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
}