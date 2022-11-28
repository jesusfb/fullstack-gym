package com.backend.controller;

import com.backend.model.User;
import com.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        super();
        this.userService = userService;
    }

    // http://localhost:8080/users/add  POST METHOD + JSON BODY
    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    // http://localhost:8080/users/all GET METHOD
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    // http://localhost:8080/users/id/1 GET METHOD
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById( @PathVariable("id") int userId)
    {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    // http://localhost:8080/users/update/id/1 PUT METHOD + JSON BODY
    @PutMapping("/update/id/{id}")
    public ResponseEntity<User> updateUser( @PathVariable("id") int userId, @RequestBody User user)
    {
        return new ResponseEntity<>(userService.updateUser(user,userId),HttpStatus.OK);
    }

    // http://localhost:8080/users/delete/id/1 DELETE METHOD
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") int userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}