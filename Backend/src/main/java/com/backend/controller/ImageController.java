package com.backend.controller;

import com.backend.model.*;
import com.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController
{
    @Autowired
    ImageService imageService;

    @GetMapping("get/image")
    public ResponseEntity<?> downloadImageFromFileSystem(@RequestParam("name") String fileName) throws IOException
    {
        byte[] imageData = imageService.downloadImageFromFileSystem(fileName);
        String imageType = imageService.getImageType(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(imageType)).body(imageData);
    }

    @PostMapping("/courses/upload/image")
    public ResponseEntity<Course> uploadImageToCourse(@RequestParam("image") MultipartFile file, @RequestParam("course_id") int course_id ) throws IOException
    {
        return new ResponseEntity<>(imageService.uploadImageToCourse(file,course_id),HttpStatus.CREATED);
    }

    @DeleteMapping ("/courses/delete/image")
    public ResponseEntity<Course> DeleteImageFromCourse(@RequestParam("course_id") int course_id )
    {
        return new ResponseEntity<>(imageService.deleteImageFromCourse(course_id),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/instructors/upload/image")
    public ResponseEntity<Instructor> uploadImageToInstructor(@RequestParam("image") MultipartFile file, @RequestParam("instructor_id") int instructor_id ) throws IOException
    {
        return new ResponseEntity<>(imageService.uploadImageToInstructor(file,instructor_id),HttpStatus.CREATED);
    }

    @DeleteMapping("/instructors/delete/image")
    public ResponseEntity<Instructor> DeleteImageFromInstructor(@RequestParam("instructor_id") int instructor_id )
    {
        return new ResponseEntity<>(imageService.deleteImageFromInstructor(instructor_id),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/upload/image")
    public ResponseEntity<User> uploadImageToUser(@RequestParam("image") MultipartFile file, @RequestParam("user_id") int user_id) throws IOException
    {
        return new ResponseEntity<>(imageService.uploadImageToUser(file,user_id),HttpStatus.CREATED);
    }

    @DeleteMapping ("/users/delete/image")
    public ResponseEntity<User> DeleteImageFromUser(@RequestParam("user_id") int user_id )
    {
        return new ResponseEntity<>(imageService.deleteImageFromUser(user_id),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/plans/upload/image")
    public ResponseEntity<Plan> uploadImageToPlan(@RequestParam("image") MultipartFile file, @RequestParam("plan_id") int plan_id ) throws IOException
    {
        return new ResponseEntity<>(imageService.uploadImageToPlan(file,plan_id),HttpStatus.CREATED);
    }

    @DeleteMapping ("/plans/delete/image")
    public ResponseEntity<Plan> DeleteImageFromPlan(@RequestParam("plan_id") int plan_id )
    {
        return new ResponseEntity<>(imageService.deleteImageFromPlan(plan_id),HttpStatus.NO_CONTENT);
    }
}


