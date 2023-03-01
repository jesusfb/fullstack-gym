package com.backend.service;

import com.backend.model.Plan;
import com.backend.model.Role;
import com.backend.model.User;
import com.backend.repository.PlanRepository;
import com.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PlanRepository planRepository;
    private UserServiceImpl userService;
    AutoCloseable autoCloseable;
    User user;
    User user1;

    @BeforeEach
    void setUp()
    {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        Plan plan = new Plan(1,"silver","desc",70,34,"URL");
        planRepository.save(plan);
        user = new User(1,"Natalia","Gkiourda", Role.ADMIN,"natalia@gmail.com","1234","Euelpidon 4","[URL]", LocalDate.now(),plan,1);
        user1 = new User(2,"Kostas","Antoniou", Role.ADMIN,"kostas@gmail.com","1234","Euelpidon 99","[URL]", LocalDate.now(),plan,1);
        userRepository.save(user);
        userRepository.save(user1);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllUsersByPlanId()
    {
        mock(User.class);
        mock(UserRepository.class);
        mock(Plan.class);
        mock(PlanRepository.class);
        userRepository.save(user);
        userRepository.save(user1);
        List<User> usersList = userRepository.findAll();
        assertThat(usersList).isNotNull();
    }

    @Test
    void getAllUsers()
    {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(
                new ArrayList<User>(Collections.singleton(user))
        );
        assertThat(userService.getAllUsers().get(0).getUser_id()).isEqualTo(user.getUser_id());
    }

    @Test
    void getUserById()
    {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUserById(1).getUser_name())
                .isEqualTo(user.getUser_name());
    }
}