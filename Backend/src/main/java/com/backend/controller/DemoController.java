package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")

    public ResponseEntity<String> sayHelloADMIN() {
        return ResponseEntity.ok("Hello from secured endpoint admin");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> sayHelloUSER() {
        return ResponseEntity.ok("Hello from secured endpoint user");
    }
}
