package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Instructor;
import com.backend.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Instructor saveInstructor(Instructor instructor)
    {
        return instructorRepository.save(new Instructor(instructor.getInstructor_name(),instructor.getInstructor_lastname(),instructor.getInstructor_email()));
    }

    @Override
    public List<Instructor> getAllInstructors()
    {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(int id)
    {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if(instructor.isPresent())
        {
            return instructor.get();
        }
        else
        {
            throw new ResourceNotFoundException("Instructor","Id",id);
        }
    }

    @Override
    public Instructor updateInstructor(Instructor instructor, int id)
    {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        existingInstructor.setInstructor_name(instructor.getInstructor_name());
        existingInstructor.setInstructor_lastname(instructor.getInstructor_lastname());
        existingInstructor.setInstructor_email(instructor.getInstructor_email());
        instructorRepository.save(existingInstructor);
        return existingInstructor;
    }

    @Override
    public void deleteInstructor(int id)
    {
        instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        instructorRepository.deleteById(id);
    }
}