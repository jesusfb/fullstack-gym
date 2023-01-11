package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.Image;
import com.backend.model.Instructor;
import com.backend.repository.ImageRepository;
import com.backend.repository.InstructorRepository;
import com.backend.tool.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    ImageRepository imageRepository;

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
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        return existingInstructor;
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

    @Override
    public Instructor uploadImage(MultipartFile file, int id) throws IOException
    {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        if(!existingInstructor.getImage_url().isEmpty())
        {
            existingInstructor = deleteImage(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingInstructor.setImage_url(file.getOriginalFilename());
        instructorRepository.save(existingInstructor);
        return existingInstructor;
    }

    @Override
    public Instructor deleteImage(int id)
    {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        String tobedeletedImageName = existingInstructor.getImage_url();
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has not been found"));
        imageRepository.delete(tobedeletedImage);
        existingInstructor.setImage_url("");
        return instructorRepository.save(existingInstructor);
    }
}