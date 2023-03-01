package com.backend.service;

import com.backend.model.Image;
import com.backend.model.Instructor;
import com.backend.repository.InstructorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
class InstructorServiceImplTest {

    @Autowired
    private InstructorRepository instructorRepository;
    private InstructorServiceImpl instructorService;
    private Instructor instructor;

    @BeforeEach
    void setUp() {
        instructor = new Instructor(1,"Natalia","Giourda",
                "natalia@gmail.com","Yoga trainer","[Url]");
    }

    @AfterEach
    void tearDown()
    {
        instructor = null;
        instructorRepository.deleteAll();
    }

    @Test
    void saveInstructor()
    {
        Instructor savedInstructor = instructorRepository.save(instructor);
        assertThat(savedInstructor).isNotNull();
        assertThat(savedInstructor.getId()).isGreaterThan(0);
    }

    @Test
    void getAllInstructors()
    {
        Instructor instructor1 = new Instructor(2,"Kostas","Antoniou",
                "kostas@gmail.com","Crossfit trainer","[Url]");
        instructorRepository.save(instructor);
        instructorRepository.save(instructor1);
        List<Instructor> instructorList = instructorRepository.findAll();
        assertThat(instructorList).isNotNull();
        assertThat(instructorList.size()).isEqualTo(2);
    }

    @Test
    void getInstructorById()
    {
        instructorRepository.save(instructor);
        Instructor instructorDB = instructorRepository.findById(instructor.getId()).get();
        assertThat(instructorDB).isNotNull();
    }

    @Test
    void updateInstructor()
    {
        instructorRepository.save(instructor);
        Instructor saveInstructor = instructorRepository.findById(instructor.getId()).get();
        saveInstructor.setInstructor_email("nat_gkiourda@gmail.com");
        saveInstructor.setInstructor_specialty("Dancer");
        Instructor updatedInstructor = instructorRepository.save(saveInstructor);
        assertThat(updatedInstructor.getInstructor_email()).isEqualTo("nat_gkiourda@gmail.com");
        assertThat(updatedInstructor.getInstructor_specialty()).isEqualTo("Dancer");
    }

    @Test
    void deleteInstructor()
    {
        instructorRepository.save(instructor);
        instructorRepository.deleteById(instructor.getId());
        Optional<Instructor> instructorOptional = instructorRepository.findById(instructor.getId());
        assertThat(instructorOptional).isEmpty();
    }
}