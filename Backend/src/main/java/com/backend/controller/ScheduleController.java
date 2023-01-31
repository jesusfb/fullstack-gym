package com.backend.controller;

import com.backend.model.Schedule;
import com.backend.request.CalendarResponse;
import com.backend.request.EventResponse;
import com.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleController
{
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/courses/schedule/add")
    public ResponseEntity<Schedule> saveSchedule(@RequestParam(value = "course_id") int course_id, @RequestBody Schedule schedule)
    {
        return new ResponseEntity<>(scheduleService.saveSchedule(course_id, schedule), HttpStatus.CREATED);
    }

    @GetMapping("/courses/schedule/get")
    public ResponseEntity<List<Schedule>> getScheduleByCourseId(@RequestParam(value = "course_id") int course_id)
    {
        return new ResponseEntity<>(scheduleService.getScheduleByCourseId(course_id), HttpStatus.OK);
    }

    @GetMapping("/courses/schedule/all")
    public ResponseEntity<List<Schedule>> getSchedule()
    {
        return new ResponseEntity<>(scheduleService.getSchedule(), HttpStatus.OK);
    }

    @PutMapping("/courses/update/schedule")
    public ResponseEntity<Schedule> updateSchedule(@RequestParam(value = "schedule_id") int schedule_id, @RequestBody Schedule schedule)
    {
        return new ResponseEntity<>(scheduleService.updateSchedule(schedule_id,schedule), HttpStatus.OK);
    }

    @DeleteMapping("schedule/delete")
    public ResponseEntity<HttpStatus> deleteScheduleById(@RequestParam("schedule_id") int schedule_id)
    {
        scheduleService.deleteSchedule(schedule_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // returns results for the next x number of weeks
    @GetMapping("/courses/schedule/all/iso8601")
    public ResponseEntity<List<CalendarResponse>> getScheduleInIso8601Format(@RequestParam(value = "weeks") int weeks)
    {
        return new ResponseEntity<>(scheduleService.getScheduleInIso8601Format(weeks), HttpStatus.OK);
    }

    @GetMapping("/courses/schedule/all/epoch")
    public ResponseEntity<List<EventResponse>> getScheduleInEpochFormat()
    {
        return new ResponseEntity<>(scheduleService.getScheduleInEpochFormat(), HttpStatus.OK);
    }
}