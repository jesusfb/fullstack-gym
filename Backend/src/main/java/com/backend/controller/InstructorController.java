package com.backend.controller;

import com.backend.model.Instructor;
import com.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController
{
    @Autowired
    InstructorService instructorService;

    @PostMapping("instructors/add")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);
    }

    @GetMapping("instructors/all")
    public ResponseEntity<List<Instructor>> getAllInstructors()
    {
        return new ResponseEntity<>(instructorService.getAllInstructors(),HttpStatus.OK);
    }

    @GetMapping("instructors/id/{id}")
    public ResponseEntity<Instructor> getInstructorById( @PathVariable("id") int instructorId)
    {
        return new ResponseEntity<>(instructorService.getInstructorById(instructorId),HttpStatus.OK);
    }

    @PutMapping("instructors/update/id/{id}")
    public ResponseEntity<Instructor> updateInstructor( @PathVariable("id") int instructorId, @RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.updateInstructor(instructor,instructorId),HttpStatus.OK);
    }

    @DeleteMapping("instructors/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteInstructorById(@PathVariable("id") int instructorId)
    {
        instructorService.deleteInstructor(instructorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}