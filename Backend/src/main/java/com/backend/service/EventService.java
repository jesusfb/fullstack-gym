package com.backend.service;

import com.backend.model.Event;

import java.util.List;

public interface EventService
{
    Event saveEvent(Event eventRequest);
    List<Event> getAll();
}
