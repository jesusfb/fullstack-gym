package com.backend.service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Schedule;
import com.backend.repository.CourseRepository;
import com.backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}