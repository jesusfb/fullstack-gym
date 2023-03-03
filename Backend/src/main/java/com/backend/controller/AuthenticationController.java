package com.backend.controller;

import com.backend.model.User;
import com.backend.request.AuthenticationRequest;
import com.backend.request.RegisterRequest;
import com.backend.response.AuthenticationResponse;
import com.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    }
<<<<<<< Updated upstream
    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password
    )
    {
=======

    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
>>>>>>> Stashed changes
        return ResponseEntity.ok(service.authenticate(email,password));
    }
}