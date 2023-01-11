package com.backend.service;

import com.backend.model.Instructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InstructorService
{
    Instructor saveInstructor (Instructor instructor);
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(int id);
    Instructor updateInstructor(Instructor instructor,int id);
    void deleteInstructor(int id);

    Instructor uploadImage(MultipartFile file, int id) throws IOException;

    Instructor deleteImage(int id);
}