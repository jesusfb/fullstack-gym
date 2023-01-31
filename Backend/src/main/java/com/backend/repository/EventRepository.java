package com.backend.repository;

import com.backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>
{
    @Query(value = "SELECT * FROM EVENTS WHERE USER_ID = ?1", nativeQuery = true)
    List<Event> findByUserId(int user_id);
    @Query(value = "SELECT * FROM EVENTS WHERE COURSE_ID = ?3 AND USER_ID = ?1 AND START_TIMESTAMP = ?2", nativeQuery = true)
    List<Event> findRegistryByUserCourseAndTimestamp(int user_id,long start_timestamp,int course_id);
}