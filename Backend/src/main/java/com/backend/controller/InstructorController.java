package com.backend.controller;

import com.backend.model.Instructor;
import com.backend.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController
{
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService)
    {
        super();
        this.instructorService = instructorService;
    }

    // http://localhost:8080/instructors/add  POST METHOD + JSON BODY
    @PostMapping("/add")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);
    }

    // http://localhost:8080/instructors/all GET  METHOD
    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors()
    {
        return new ResponseEntity<>(instructorService.getAllInstructors(),HttpStatus.OK);
    }

    // http://localhost:8080/instructors/id/1 GET METHOD
    @GetMapping("/id/{id}")
    public ResponseEntity<Instructor> getInstructorById( @PathVariable("id") int instructorId)
    {
        return new ResponseEntity<>(instructorService.getInstructorById(instructorId),HttpStatus.OK);
    }

    // http://localhost:8080/instructors/update/id/1 PUT METH0D + JSON BODY
    @PutMapping("/update/id/{id}")
    public ResponseEntity<Instructor> updateInstructor( @PathVariable("id") int instructorId, @RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.updateInstructor(instructor,instructorId),HttpStatus.OK);
    }

    // http://localhost:8080/instructors/delete/id/1 DELETE METHOD
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<HttpStatus> deleteInstructorById(@PathVariable("id") int instructorId)
    {
        instructorService.deleteInstructor(instructorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}