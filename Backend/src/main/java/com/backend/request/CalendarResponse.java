package com.backend.request;

import lombok.Data;

@Data
public class CalendarResponse
{
    private String course_name;
    private String start;
    private String end;
    private String room;
}
