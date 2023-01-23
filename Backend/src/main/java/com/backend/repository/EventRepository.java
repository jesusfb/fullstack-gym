package com.backend.repository;

import com.backend.model.Event;
import com.backend.model.EventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, EventId>
{
    @Query(value = "SELECT * FROM EVENTS WHERE USER_ID = ?1", nativeQuery = true)
    List<Event> findByUserId(int user_id);
    @Query(value = "SELECT * FROM EVENTS WHERE USER_ID = ?1 AND START_TIMESTAMP = ?2", nativeQuery = true)
    Event findByCompositeKey(int user_id,long start_timestamp);
}