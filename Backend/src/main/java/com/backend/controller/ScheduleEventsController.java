package com.backend.controller;

import com.backend.model.ScheduleEvents;
import com.backend.response.ScheduleEventsResponse;
import com.backend.service.ScheduleEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleEventsController
{
    @Autowired
    ScheduleEventsService scheduleEventsService;

    @PostMapping("/courses/events/add")
    public ResponseEntity<ScheduleEvents> saveEventForCourse(@RequestParam(value = "course_id") int course_id, @RequestBody ScheduleEvents scheduleEventsRequest)
    {
        return new ResponseEntity<>(scheduleEventsService.saveEventForCourse(course_id, scheduleEventsRequest), HttpStatus.CREATED);
    }

    @GetMapping("/courses/events/get")
    public ResponseEntity<List<ScheduleEvents>> getAllEventsByCourseId(@RequestParam(value = "course_id") int course_id)
    {
        return new ResponseEntity<>(scheduleEventsService.getAllEventsByCourseId(course_id), HttpStatus.OK);
    }

    @GetMapping("/courses/events/all")
    public ResponseEntity<List<ScheduleEvents>> getAllEvents()
    {
        return new ResponseEntity<>(scheduleEventsService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/courses/events/allEpoch")
    public ResponseEntity<List<ScheduleEventsResponse>> getAllEpochEvents()
    {
        return new ResponseEntity<>(scheduleEventsService.getAllEventsInEpochFormat(), HttpStatus.OK);
    }

    @PutMapping("/courses/update/events")
    public ResponseEntity<ScheduleEvents> updateEvent(@RequestParam(value = "event_id") int event_id,@RequestBody ScheduleEvents scheduleEvent)
    {
        return new ResponseEntity<>(scheduleEventsService.updateEvent(event_id,scheduleEvent), HttpStatus.OK);
    }

    @DeleteMapping("events/delete")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam("event_id") int event_id)
    {
        scheduleEventsService.deleteEvent(event_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}