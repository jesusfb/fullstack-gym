package com.backend.service;

import com.backend.model.ScheduleEvents;
import com.backend.response.ScheduleEventsResponse;

import java.util.List;

public interface ScheduleEventsService
{
    ScheduleEvents saveEventForCourse(int course_id, ScheduleEvents scheduleEventsRequest);

    List<ScheduleEvents> getAllEventsByCourseId(int course_id);

    List<ScheduleEvents> getAllEvents();

    ScheduleEvents updateEvent(int event_id, ScheduleEvents scheduleEvent);

    void deleteEvent(int event_id);

    List<ScheduleEventsResponse> getAllEventsInEpochFormat();
}
