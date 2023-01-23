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
    private int user_id;
    @Id
    private long start_timestamp;
    private long end_timestamp;
    private int course_id;
}