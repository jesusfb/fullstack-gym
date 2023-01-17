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
@CrossOrigin(origins = "http://localhost:8080")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/plans/users/add")
    public ResponseEntity<User> saveUser(@RequestParam(value = "plan_id") int plan_id,@RequestBody User userRequest)
    {
        return new ResponseEntity<>(userService.saveUser(plan_id,userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/plans/users/all")
    public ResponseEntity<List<User>> getAllUsersByPlanId(@RequestParam(value = "plan_id") int plan_id)
    {
        return new ResponseEntity<>(userService.getAllUsersByPlanId(plan_id),HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<User> getUserById( @RequestParam("user_id") int user_id)
    {
        return new ResponseEntity<>(userService.getUserById(user_id),HttpStatus.OK);
    }

    @GetMapping("users/all")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("users/update")
    public ResponseEntity<User> updateUser( @RequestParam("user_id") int user_id, @RequestBody User user)
    {
        return new ResponseEntity<>(userService.updateUser(user,user_id),HttpStatus.OK);
    }

    @PutMapping ("users/update/plan")
    public ResponseEntity<User> changePlanToUser( @RequestParam("user_id") int user_id, @RequestParam("plan_id") int plan_id)
    {
        return new ResponseEntity<>(userService.changePlantoUser(user_id,plan_id),HttpStatus.OK);
    }

    @DeleteMapping("users/delete")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam("user_id") int user_id)
    {
        userService.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}