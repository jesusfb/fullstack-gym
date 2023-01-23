package com.backend.service;

import com.backend.exception.GymPolicyException;
import com.backend.exception.RegistryAlreadyExistsException;
import com.backend.model.Event;
import com.backend.repository.EventRepository;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class EventServiceImpl implements EventService
{
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Event register(Event eventRequest)
    {
        if(eventRepository.findByCompositeKey(eventRequest.getUser_id(),eventRequest.getStart_timestamp()) != null)
        {
            throw new RegistryAlreadyExistsException("Event with Composite Primary Key = (" + eventRequest.getUser_id()+","+eventRequest.getStart_timestamp()+")" + " already exists");
        }
        List<Event> already_registered_events_by_user = eventRepository.findByUserId(eventRequest.getUser_id());
        if(already_registered_events_by_user != null)
        {
            // find previous or same Monday DateTime in epoch seconds
            String day = "Monday";
            String dayUppercase = day.toUpperCase() ;
            DayOfWeek dow = DayOfWeek.valueOf(dayUppercase);
            LocalDate previousSundayDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(dow));
            LocalTime previousSundayTime = LocalTime.of(00, 00);
            LocalDateTime previousSundayDateTime = LocalDateTime.of(previousSundayDate, previousSundayTime);
            long previousSundayDateTimeInSeconds = previousSundayDateTime.toEpochSecond(ZoneOffset.UTC);
            int counter = 0;
            for(int i=0; i<already_registered_events_by_user.size(); i++)
            {
                if(already_registered_events_by_user.get(i).getStart_timestamp() > previousSundayDateTimeInSeconds && already_registered_events_by_user.get(i).getCourse_id() == eventRequest.getCourse_id())
                {
                    counter++;
                }
            }
            if(counter >= 2)
            {
                throw new GymPolicyException("You cant register to more than 2 events of the same course each week");
            }
        }
        return eventRepository.save(eventRequest);
    }

    @Override
    public List<Event> getAll()
    {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllByUserId(int user_id)
    {
        return eventRepository.findByUserId(user_id);
    }

    @Override
    public void unregister(Event eventRequest)
    {
        long now_timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        if(eventRequest.getStart_timestamp() < now_timestamp)
        {
            throw new GymPolicyException("You cant cancel this Event.");
        }
        eventRepository.delete(eventRequest);
    }
}