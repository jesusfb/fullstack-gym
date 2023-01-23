package com.backend.request;

import lombok.Data;

@Data
public class EventResponse
{
    private int course_id;
    private long start_timestamp;
    private long end_timestamp;
    private String room;
}
