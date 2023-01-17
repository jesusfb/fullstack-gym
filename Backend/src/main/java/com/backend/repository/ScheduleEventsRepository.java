package com.backend.repository;

import com.backend.model.ScheduleEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleEventsRepository extends JpaRepository<ScheduleEvents,Integer>
{
    List<ScheduleEvents> findByCourse_Id(int course_id);
}