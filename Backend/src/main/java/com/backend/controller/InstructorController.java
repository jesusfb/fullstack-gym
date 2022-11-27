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

    // http://localhost:8080/instructors  POST + JSON BODY
    @PostMapping("")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);
    }

    // http://localhost:8080/instructors GET
    @GetMapping("")
    public List<Instructor> getAllInstructors()
    {
        return instructorService.getAllInstructors();
    }

    // http://localhost:8080/instructors/1 GET
    @GetMapping("{id}")
    public ResponseEntity<Instructor> getInstructorById( @PathVariable("id") int instructorId)
    {
        return new ResponseEntity<>(instructorService.getInstructorById(instructorId),HttpStatus.OK);
    }

    // http://localhost:8080/instructors/1 PUT + JSON BODY
    @PutMapping("{id}")
    public ResponseEntity<Instructor> updateInstructor( @PathVariable("id") int instructorId, @RequestBody Instructor instructor)
    {
        return new ResponseEntity<>(instructorService.updateInstructor(instructor,instructorId),HttpStatus.OK);
    }

    // http://localhost:8080/instructors/1 DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable("id") int instructorId)
    {
        instructorService.deleteInstructor(instructorId);
        return new ResponseEntity<>("Instructor has been deleted successfully",HttpStatus.OK);
    }
}