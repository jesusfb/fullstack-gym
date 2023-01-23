package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.Schedule;
import com.backend.repository.CourseRepository;
import com.backend.repository.ScheduleRepository;
import com.backend.request.CalendarResponse;
import com.backend.request.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService
{
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule saveSchedule(int course_id, Schedule scheduleRequest)
    {
        Schedule schedule = courseRepository.findById(course_id).map(course ->
                {
                    scheduleRequest.setCourse(course);
                    return scheduleRepository.save(scheduleRequest);
                }
        ).orElseThrow( () -> new ResourceNotFoundException("Course","Id",course_id));
        return schedule;
    }

    @Override
    public List<Schedule> getScheduleByCourseId(int course_id)
    {
        if (!courseRepository.existsById(course_id))
        {
            throw new ResourceNotFoundException("Course","Id",course_id);
        }

        List<Schedule> scheduleList = scheduleRepository.findByCourse_Id(course_id);
        scheduleList.forEach(schedule ->
        {
            schedule.setScheduled_course(schedule.getCourse().getCourse_name());
        });
        return scheduleList;
    }

    @Override
    public List<Schedule> getSchedule()
    {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        scheduleList.forEach(schedule ->
        {
            schedule.setScheduled_course(schedule.getCourse().getCourse_name());
        });
        return scheduleList;
    }

    @Override
    public Schedule updateSchedule(int schedule_id, Schedule scheduleEvent) {
        Schedule existingSchedule = scheduleRepository.findById(schedule_id).orElseThrow( () -> new ResourceNotFoundException("Schedule","Id",schedule_id));
        existingSchedule.setScheduled_day(scheduleEvent.getScheduled_day());
        existingSchedule.setScheduled_start_time(scheduleEvent.getScheduled_start_time());
        existingSchedule.setScheduled_end_time(scheduleEvent.getScheduled_end_time());
        existingSchedule.setScheduled_room(scheduleEvent.getScheduled_room());
        existingSchedule.setScheduled_course(existingSchedule.getCourse().getCourse_name());
        scheduleRepository.save(existingSchedule);
        return existingSchedule;
    }

    @Override
    public void deleteSchedule(int schedule_id)
    {
        scheduleRepository.findById(schedule_id).orElseThrow( () -> new ResourceNotFoundException("Schedule","Id",schedule_id));
        scheduleRepository.deleteById(schedule_id);
    }

    @Override
    public List<CalendarResponse> getScheduleInIso8601Format(int weeks)
    {
        List<CalendarResponse> responseList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findAll();
        scheduleList.forEach(scheduleEvent ->
        {
            Course scheduled_course = scheduleEvent.getCourse();
            // retrieve data from table Schedule for every single course
            String day = scheduleEvent.getScheduled_day();
            String dayUppercase = day.toUpperCase() ;
            DayOfWeek dow = DayOfWeek.valueOf(dayUppercase);
            String start_time = scheduleEvent.getScheduled_start_time();
            String end_time = scheduleEvent.getScheduled_end_time();
            // calculate Date and Time in iso8601 format for the next 'i' weeks
            LocalDate localDate = LocalDate.now();
            for(int i=1; i<=weeks; i++)
            {
                CalendarResponse calendarResponse = new CalendarResponse();
                localDate = localDate.with(TemporalAdjusters.nextOrSame(dow));
                LocalTime startLocalTime = LocalTime.parse(start_time);
                LocalDateTime startLocalDateTime = LocalDateTime.of(localDate, startLocalTime);
                calendarResponse.setStart(startLocalDateTime.toString());
                LocalTime endLocalTime = LocalTime.parse(end_time);
                LocalDateTime endlocalDateTime = LocalDateTime.of(localDate, endLocalTime);
                calendarResponse.setEnd(endlocalDateTime.toString());
                // set other parameters required to calendarRequest
                calendarResponse.setRoom(scheduleEvent.getScheduled_room());
                calendarResponse.setCourse_name(scheduled_course.getCourse_name());
                responseList.add(calendarResponse);
            }
        });
        return responseList;
    }

    @Override
    public List<EventResponse> getScheduleInEpochFormat()
    {
        List<EventResponse> responseList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleRepository.findAll();
        scheduleList.forEach(scheduleEvent ->
        {
            // retrieve data from table Schedule for every single course
            Course scheduled_course = scheduleEvent.getCourse();
            String day = scheduleEvent.getScheduled_day();
            String start_time = scheduleEvent.getScheduled_start_time();
            String end_time = scheduleEvent.getScheduled_end_time();
            String dayUppercase = day.toUpperCase() ;
            DayOfWeek dow = DayOfWeek.valueOf(dayUppercase);
            LocalDate localDate = LocalDate.now();
            // calculate Date and Time in iso8601 format for the next 'i' weeks
            EventResponse eventResponse = new EventResponse();
            localDate = localDate.with(TemporalAdjusters.nextOrSame(dow));
            LocalTime startLocalTime = LocalTime.parse(start_time);
            LocalDateTime startLocalDateTime = LocalDateTime.of(localDate, startLocalTime);
            long starttimeInSeconds = startLocalDateTime.toEpochSecond(ZoneOffset.UTC);
            eventResponse.setStart_timestamp(starttimeInSeconds);
            LocalTime endLocalTime = LocalTime.parse(end_time);
            LocalDateTime endlocalDateTime = LocalDateTime.of(localDate, endLocalTime);
            long endtimeInSeconds = endlocalDateTime.toEpochSecond(ZoneOffset.UTC);
            eventResponse.setEnd_timestamp(endtimeInSeconds);
            // set other parameters required to calendarRequest
            eventResponse.setCourse_id(scheduled_course.getId());
            eventResponse.setRoom(scheduleEvent.getScheduled_room());
            responseList.add(eventResponse);
        });
        return responseList;
    }
}