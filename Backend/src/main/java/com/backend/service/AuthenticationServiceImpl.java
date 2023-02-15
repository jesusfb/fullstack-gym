package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.PlanRepository;
import com.backend.security.JwtService;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.repository.UserRepository;
import com.backend.request.AuthenticationRequest;
import com.backend.request.RegisterRequest;
import com.backend.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService
{
    private final PlanRepository planRepository;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest request) {
        int plan_id = request.getPlan_id();
        User user = planRepository.findById(plan_id).map( plan -> {
                    var user1 = User.builder()
                            .user_name(request.getFirstname())
                            .user_lastname(request.getLastname())
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .role(Role.USER)
                            .user_address(request.getPhysical_address())
                            .registered_date(LocalDate.now())
                            .plan(plan)
                            .image_url("http://localhost:8080/api/get/image?name=no_image.png")
                            .build();
                    return repository.save(user1);
                }
        ).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",plan_id));

        return user;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
