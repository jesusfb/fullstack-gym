package com.backend.service;

import com.backend.model.Event;

import java.util.List;

public interface EventService
{
    Event saveEvent(Event eventRequest,int user_id,int course_id);
    List<Event> getAll();
    List<Event> getAllByUserId(int user_id);
    void unregister(int user_id,int course_id,Event eventRequest);
}