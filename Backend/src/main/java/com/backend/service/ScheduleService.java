package com.backend.service;

import com.backend.model.Schedule;

import java.util.List;

public interface ScheduleService
{
    Schedule saveSchedule(int course_id, Schedule scheduleRequest);

    List<Schedule> getScheduleByCourseId(int course_id);

    List<Schedule> getSchedule();

    Schedule updateSchedule(int schedule_id, Schedule scheduleEvent);

    void deleteSchedule(int schedule_id);
}
