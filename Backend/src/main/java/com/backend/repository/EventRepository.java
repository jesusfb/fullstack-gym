package com.backend.repository;

import com.backend.model.Event;
import com.backend.model.EventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, EventId>
{

}
