package com.backend.service;

import com.backend.model.Event;
import com.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService
{
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event saveEvent(Event eventRequest)
    {
        return eventRepository.save(eventRequest);
    }

    @Override
    public List<Event> getAll()
    {
        return eventRepository.findAll();
    }
}
