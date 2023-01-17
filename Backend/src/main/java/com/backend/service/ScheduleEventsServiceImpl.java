package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.ScheduleEvents;
import com.backend.repository.CourseRepository;
import com.backend.repository.ScheduleEventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScheduleEventsServiceImpl implements ScheduleEventsService
{
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ScheduleEventsRepository scheduleEventsRepository;

    @Override
    public ScheduleEvents saveEventForCourse(int course_id, ScheduleEvents scheduleEventsRequest)
    {
        ScheduleEvents scheduleEvents = courseRepository.findById(course_id).map(course ->
                {
                    scheduleEventsRequest.setCourse(course);
                    return scheduleEventsRepository.save(scheduleEventsRequest);
                }
        ).orElseThrow( () -> new ResourceNotFoundException("Course","Id",course_id));
        return scheduleEvents;
    }

    @Override
    public List<ScheduleEvents> getAllEventsByCourseId(int course_id)
    {
        if (!courseRepository.existsById(course_id))
        {
            throw new ResourceNotFoundException("Course","Id",course_id);
        }

        List<ScheduleEvents> scheduleEventsList = scheduleEventsRepository.findByCourse_Id(course_id);
        scheduleEventsList.forEach(scheduleEvent ->
        {
            scheduleEvent.setScheduled_course(scheduleEvent.getCourse().getCourse_name());
        });
        return scheduleEventsList;
    }

    @Override
    public List<ScheduleEvents> getAllEvents()
    {
        List<ScheduleEvents> scheduleEventsList = scheduleEventsRepository.findAll();
        scheduleEventsList.forEach( scheduleEvent ->
        {
            scheduleEvent.setScheduled_course(scheduleEvent.getCourse().getCourse_name());
        });
        return scheduleEventsList;
    }

    @Override
    public ScheduleEvents updateEvent(int event_id, ScheduleEvents scheduleEvent) {
        ScheduleEvents existingEvent = scheduleEventsRepository.findById(event_id).orElseThrow( () -> new ResourceNotFoundException("Scheduled Event","Id",event_id));
        existingEvent.setScheduled_day(scheduleEvent.getScheduled_day());
        existingEvent.setScheduled_start_time(scheduleEvent.getScheduled_start_time());
        existingEvent.setScheduled_end_time(scheduleEvent.getScheduled_end_time());
        existingEvent.setScheduled_room(scheduleEvent.getScheduled_room());
        existingEvent.setScheduled_course(existingEvent.getCourse().getCourse_name());
        scheduleEventsRepository.save(existingEvent);
        return existingEvent;
    }

    @Override
    public void deleteEvent(int event_id)
    {
        scheduleEventsRepository.findById(event_id).orElseThrow( () -> new ResourceNotFoundException("Event","Id",event_id));
        scheduleEventsRepository.deleteById(event_id);
    }
}