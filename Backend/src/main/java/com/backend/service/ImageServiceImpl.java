package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.*;
import com.backend.repository.*;
import com.backend.tool.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService
{
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanRepository planRepository;

    private String ImageAPIgetImageURL = "http://localhost:8080/api/get/image?name=";

    @Override
    public Course uploadImageToCourse(MultipartFile file, int id) throws IOException {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        if(!existingCourse.getImage_url().isEmpty())
        {
            existingCourse = deleteImageFromCourse(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingCourse.setImage_url(ImageAPIgetImageURL + file.getOriginalFilename());
        courseRepository.save(existingCourse);
        return existingCourse;    }

    @Override
    public Course deleteImageFromCourse(int id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        String tobedeletedImageURL = existingCourse.getImage_url();
        String[] arrOfStr = tobedeletedImageURL.split("=");
        String tobedeletedImageName = arrOfStr[1];
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has mot been found"));
        imageRepository.delete(tobedeletedImage);
        existingCourse.setImage_url("");
        return courseRepository.save(existingCourse);
    }

    @Override
    public Instructor uploadImageToInstructor(MultipartFile file, int id) throws IOException {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        if(!existingInstructor.getImage_url().isEmpty())
        {
            existingInstructor = deleteImageFromInstructor(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingInstructor.setImage_url(ImageAPIgetImageURL + file.getOriginalFilename());
        instructorRepository.save(existingInstructor);
        return existingInstructor;
    }

    @Override
    public Instructor deleteImageFromInstructor(int id) {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Instructor","Id",id));
        String tobedeletedImageURL = existingInstructor.getImage_url();
        String[] arrOfStr = tobedeletedImageURL.split("=");
        String tobedeletedImageName = arrOfStr[1];
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has not been found"));
        imageRepository.delete(tobedeletedImage);
        existingInstructor.setImage_url("");
        return instructorRepository.save(existingInstructor);
    }

    @Override
    public Plan uploadImageToPlan(MultipartFile file, int id) throws IOException {
        Plan existingPlan = planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        if(!existingPlan.getImage_url().isEmpty())
        {
            existingPlan = deleteImageFromPlan(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingPlan.setImage_url(ImageAPIgetImageURL + file.getOriginalFilename());
        planRepository.save(existingPlan);
        return existingPlan;
    }

    @Override
    public Plan deleteImageFromPlan(int id) {
        Plan existingPlan = planRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",id));
        String tobedeletedImageURL = existingPlan.getImage_url();
        String[] arrOfStr = tobedeletedImageURL.split("=");
        String tobedeletedImageName = arrOfStr[1];
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has mot been found"));
        imageRepository.delete(tobedeletedImage);
        existingPlan.setImage_url("");
        return planRepository.save(existingPlan);
    }

    @Override
    public User uploadImageToUser(MultipartFile file, int id) throws IOException {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        if(!existingUser.getImage_url().isEmpty())
        {
            existingUser = deleteImageFromUser(id);
        }
        imageRepository.save(new Image(file.getOriginalFilename(),file.getContentType(), ImageTool.compressImage(file.getBytes())));
        existingUser.setImage_url(ImageAPIgetImageURL + file.getOriginalFilename());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public User deleteImageFromUser(int id) {
        User existingUser = userRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("User","Id",id));
        String tobedeletedImageURL = existingUser.getImage_url();
        String[] arrOfStr = tobedeletedImageURL.split("=");
        String tobedeletedImageName = arrOfStr[1];
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has not been found"));
        imageRepository.delete(tobedeletedImage);
        existingUser.setImage_url("");
        return userRepository.save(existingUser);
    }

    @Override
    public Optional<Image> getImage(String name)
    {
        final Optional<Image> dbImage = Optional.ofNullable(imageRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Image with name = " + name + " has not been found")));
        return dbImage;
    }
}
