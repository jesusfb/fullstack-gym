package com.backend.service;

import com.backend.model.Instructor;
import java.util.List;

public interface InstructorService
{
    Instructor saveInstructor (Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(int id);
    Instructor updateInstructor(Instructor instructor,int id);
    void deleteInstructor(int id);
}