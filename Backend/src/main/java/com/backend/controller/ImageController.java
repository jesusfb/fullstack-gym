package com.backend.controller;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.*;
import com.backend.repository.ImageRepository;
import com.backend.service.CourseService;
import com.backend.service.InstructorService;
import com.backend.service.PlanService;
import com.backend.service.UserService;
import com.backend.tool.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ImageController
{
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    InstructorService instructorService;

    @Autowired
    UserService userService;

    @Autowired
    PlanService planService;

    @PostMapping("/course/id/{id}/upload/image")
    public ResponseEntity<Course> uploadImageToCourse(@RequestParam("image") MultipartFile file, @PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(courseService.uploadImage(file,id),HttpStatus.CREATED);
    }

    @DeleteMapping ("/course/id/{id}/delete/image")
    public ResponseEntity<Course> DeleteImageFromCourse(@PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(courseService.deleteImage(id),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/instructor/id/{id}/upload/image")
    public ResponseEntity<Instructor> uploadImageToInstructor(@RequestParam("image") MultipartFile file, @PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(instructorService.uploadImage(file,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/instructor/id/{id}/delete/image")
    public ResponseEntity<Instructor> DeleteImageFromInstructor(@PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(instructorService.deleteImage(id),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/id/{id}/upload/image")
    public ResponseEntity<User> uploadImageToUser(@RequestParam("image") MultipartFile file, @PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(userService.uploadImage(file,id),HttpStatus.CREATED);
    }

    @DeleteMapping ("/user/id/{id}/delete/image")
    public ResponseEntity<User> DeleteImageFromUser(@PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(userService.deleteImage(id),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/plan/id/{id}/upload/image")
    public ResponseEntity<Plan> uploadImageToPlan(@RequestParam("image") MultipartFile file, @PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(planService.uploadImage(file,id),HttpStatus.CREATED);
    }

    @DeleteMapping ("/plan/id/{id}/delete/image")
    public ResponseEntity<Plan> DeleteImageFromPlan(@PathVariable("id") int id ) throws IOException
    {
        return new ResponseEntity<>(planService.deleteImage(id),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException
    {
        final Optional<Image> dbImage = Optional.ofNullable(imageRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Image with name = " + name + " has not been found")));

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageTool.decompressImage(dbImage.get().getImage()));
    }
}


