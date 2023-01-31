package com.backend.service;

import com.backend.exception.GymPolicyException;
import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Course;
import com.backend.model.Event;
import com.backend.model.Plan;
import com.backend.model.User;
import com.backend.repository.CourseRepository;
import com.backend.repository.EventRepository;
import com.backend.repository.PlanRepository;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService
{
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public Event saveEvent(long start_tmp,long end_tmp,int user_id,int course_id)
    {
        // check if user and course exists by id
        User user = userRepository.findById(user_id).orElseThrow( () -> new ResourceNotFoundException("User","Id",user_id));
        Course course = courseRepository.findById(course_id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",course_id));
        // check if user can register to course
        if(checkIfCourseBelongsToPlan(user.getPlan().getId(),course_id))
        {
            // check if registry already exists in database
            if(checkIfRegistryExists(user_id,course_id,start_tmp))
            {
                // check if user can register to this course concerning hours limit policy
                if(checkIfUserCanRegister(user_id,course_id))
                {
                    Event event = new Event();
                    event.setUser(user);
                    event.setCourse(course);
                    event.setStart_timestamp(start_tmp);
                    event.setEnd_timestamp(end_tmp);
                    return eventRepository.save(event);
                }
            }
        }
        return null;
    }

    private boolean checkIfCourseBelongsToPlan(int plan_id,int course_id)
    {
        Course course = courseRepository.findById(course_id).orElseThrow( () -> new ResourceNotFoundException("Course","Id",course_id));
        Plan plan = planRepository.findById(plan_id).orElseThrow( () -> new ResourceNotFoundException("Plan","Id",plan_id));
        Set<Course> courseSet = plan.getCourseSet();
        if(!courseSet.contains(course))
        {
            throw new GymPolicyException("You cant register to this course because it is not belong to your plan");
        }
        return true;
    }

    private boolean checkIfRegistryExists(int user_id, int course_id, long timestamp)
    {
        if(eventRepository.findRegistryByUserCourseAndTimestamp(user_id,timestamp,course_id) == null)
        {
            throw new ResourceNotFoundException("Event with user_id = " + user_id + " and course_id = " + course_id + " and start time = " + timestamp + "has not been found");
        }
        return true;
    }

    private boolean checkIfUserCanRegister(int user_id,int course_id)
    {
        List<Event> already_registered_events_by_user = eventRepository.findByUserId(user_id);
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
                if(already_registered_events_by_user.get(i).getStart_timestamp() > previousSundayDateTimeInSeconds && already_registered_events_by_user.get(i).getCourse().getId() == course_id)
                {
                    counter++;
                }
            }
            if(counter >= 2)
            {
                throw new GymPolicyException("You cant register to more than 2 events of the same course each week");
            }
        }
        return true;
    }

    @Override
    public List<Event> getAll()
    {
        List<Event> events = eventRepository.findAll();
        events.forEach(event -> {
                    event.setCourse_id(event.getCourse().getId());
                    event.setUser_id(event.getUser().getUser_id());
                }
        );
        return events;
    }

    @Override
    public List<Event> getAllByUserId(int user_id)
    {
        List<Event> events = eventRepository.findByUserId(user_id);
        events.forEach(event -> {
                    event.setCourse_id(event.getCourse().getId());
                    event.setUser_id(event.getUser().getUser_id());
                }
        );
        return events;
    }

    @Override
    public void unregister(int user_id,int course_id, long start_tmp)
    {
        List<Event> events = eventRepository.findRegistryByUserCourseAndTimestamp(user_id,start_tmp,course_id);
        long now_timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        if(events.get(0).getStart_timestamp() < now_timestamp)
        {
            throw new GymPolicyException("You cant cancel this Event.");
        }
        eventRepository.delete(events.get(0));
    }
}