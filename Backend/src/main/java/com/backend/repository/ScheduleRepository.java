package com.backend.repository;

import com.backend.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer>
{
    List<Schedule> findByCourse_Id(int course_id);
}