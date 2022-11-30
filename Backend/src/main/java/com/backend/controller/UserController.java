package com.backend.controller;

import com.backend.model.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/plans/{plan_id}/users/add")
    public ResponseEntity<User> saveUser(@PathVariable(value = "plan_id") int plan_id,@RequestBody User userRequest)
    {
        return new ResponseEntity<>(userService.saveUser(plan_id,userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/plans/{plan_id}/users/all")
    public ResponseEntity<List<User>> getAllUsersByPlanId(@PathVariable(value = "plan_id") int plan_id)
    {
        return new ResponseEntity<>(userService.getAllUsersByPlanId(plan_id),HttpStatus.OK);
    }

    @GetMapping("users/id/{id}")
    public ResponseEntity<User> getUserById( @PathVariable("id") int userId)
    {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @PutMapping("users/update/id/{id}")
    public ResponseEntity<User> updateUser( @PathVariable("id") int userId, @RequestBody User user)
    {
        return new ResponseEntity<>(userService.updateUser(user,userId),HttpStatus.OK);
    }

    @DeleteMapping("users/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") int userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}