package com.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(EventId.class)
public class Event
{
    @Id
    private int event_id;
    @Id
    private int user_id;

    private String start_datetime;
    private String end_datetime;
    private long start_timestamp;
    private long end_timestamp;
}