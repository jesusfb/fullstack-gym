package com.backend.controller;

import com.backend.model.Event;
import com.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController
{
    @Autowired
    EventService eventService;

    @PostMapping("/events/add")
    public ResponseEntity<Event> saveEvent(@RequestBody Event eventRequest)
    {
        return new ResponseEntity<>(eventService.register(eventRequest), HttpStatus.CREATED);
    }

    @GetMapping("/events/all")
    public ResponseEntity<List<Event>> getAllEvents()
    {
        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllByUserId(@RequestParam("user_id") int user_id)
    {
        return new ResponseEntity<>(eventService.getAllByUserId(user_id), HttpStatus.OK);
    }

    @DeleteMapping("/events/unregister")
    public ResponseEntity<HttpStatus> unregister(@RequestBody Event eventRequest)
    {
        eventService.unregister(eventRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
