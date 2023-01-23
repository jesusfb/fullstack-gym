package com.backend.service;

import com.backend.model.Event;

import java.util.List;

public interface EventService
{
    Event register(Event eventRequest);
    List<Event> getAll();
    List<Event> getAllByUserId(int user_id);
    void unregister(Event eventRequest);
}