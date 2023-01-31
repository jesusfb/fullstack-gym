package com.backend.service;

import com.backend.model.Schedule;
import com.backend.request.CalendarResponse;
import com.backend.request.EventResponse;

import java.util.List;

public interface ScheduleService
{
    Schedule saveSchedule(int course_id, Schedule scheduleRequest);
    List<Schedule> getScheduleByCourseId(int course_id);
    List<Schedule> getSchedule();
    Schedule updateSchedule(int schedule_id, Schedule scheduleEvent);
    void deleteSchedule(int schedule_id);
    List<CalendarResponse> getScheduleInIso8601Format(int weeks);
    List<EventResponse> getScheduleInEpochFormat();
}
