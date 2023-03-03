package com.backend.service;

import com.backend.model.*;
import com.backend.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PlanRepository planRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private InstructorRepository instructorRepository;

    private EventService eventService;

    private Course course;
    private Plan plan;
    private User user;
    private Instructor instructor;
    private Event event;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp()
    {
        autoCloseable = MockitoAnnotations.openMocks(this);
        eventService = new EventServiceImpl(eventRepository);
        Plan plan = new Plan(1,"silver","desc",70,34,"URL");
        planRepository.save(plan);
        Set<Plan> planSet = new HashSet<>();
        planSet.add(plan);
        user = new User(1,"Natalia","Gkiourda", Role.ADMIN,"natalia@gmail.com","1234","Euelpidon 4","[URL]", LocalDate.now(),plan,1);
        userRepository.save(user);
        course = new Course(1,"Pilates","Beginners","[URL]",instructor,planSet);
        courseRepository.save(course);
        event = new Event(1,user,course,1,1,111111,222222);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        planRepository.deleteAll();
        courseRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void saveEvent()
    {
        eventRepository.save(event);
    }

    @Test
    void getAll()
    {
        mock(Event.class);
        mock(EventRepository.class);
        when(eventRepository.findAll()).thenReturn(
                new ArrayList<Event>(Collections.singleton(event))
        );
        assertThat(eventService.getAll().get(0).getEvent_id()).isEqualTo(event.getEvent_id());
    }
}