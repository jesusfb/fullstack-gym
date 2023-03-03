package com.backend.service;

import com.backend.model.Event;

import java.util.List;

public interface EventService
{
    Event saveEvent(long start_tmp,long end_tmp,int user_id,int course_id,String room);
    List<Event> getAll();
    List<Event> getAllByUserId(int user_id);
    void unregister(int user_id,int course_id,long start_tmp);
}