package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.*;
import com.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private final String IMAGE_STORAGE_PATH = System.getProperty("user.dir") + "\\images\\";

    private void uploadImageToFileSystem(MultipartFile file) throws IOException
    {
        String filePath = IMAGE_STORAGE_PATH + file.getOriginalFilename();
        Image image = new Image(file.getOriginalFilename(),file.getContentType(),filePath);
        file.transferTo(new File(filePath));
        imageRepository.save(image);
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException
    {
        Optional<Image> image = imageRepository.findByName(fileName);
        String filePath = IMAGE_STORAGE_PATH + image.get().getName();
        byte[] returned_image = Files.readAllBytes(new File(filePath).toPath());
        return returned_image;
    }

    @Override
    public String getImageType(String fileName)
    {
        Optional<Image> image = imageRepository.findByName(fileName);
        return image.get().getType();
    }

    @Override
    public Course uploadImageToCourse(MultipartFile file, int id) throws IOException
    {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        if(!existingCourse.getImage_url().isEmpty())
        {
            existingCourse = deleteImageFromCourse(id);
        }
        uploadImageToFileSystem(file);
        existingCourse.setImage_url(ImageAPIgetImageURL + file.getOriginalFilename());
        courseRepository.save(existingCourse);
        return existingCourse;
    }

    @Override
    public Course deleteImageFromCourse(int id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",id));
        String tobedeletedImageURL = existingCourse.getImage_url();
        String[] arrOfStr = tobedeletedImageURL.split("=");
        String tobedeletedImageName = arrOfStr[1];
        Image tobedeletedImage = imageRepository.findByName(tobedeletedImageName).orElseThrow( () -> new ResourceNotFoundException("Image with Name = " + tobedeletedImageName + "has mot been found"));
        File myObj = new File(IMAGE_STORAGE_PATH + tobedeletedImage.getName());
        myObj.delete();
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
        uploadImageToFileSystem(file);
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
        File myObj = new File(IMAGE_STORAGE_PATH + tobedeletedImage.getName());
        myObj.delete();
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
        uploadImageToFileSystem(file);
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
        File myObj = new File(IMAGE_STORAGE_PATH + tobedeletedImage.getName());
        myObj.delete();
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
        uploadImageToFileSystem(file);
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
        File myObj = new File(IMAGE_STORAGE_PATH + tobedeletedImage.getName());
        myObj.delete();
        imageRepository.delete(tobedeletedImage);
        existingUser.setImage_url("");
        return userRepository.save(existingUser);
    }
}
