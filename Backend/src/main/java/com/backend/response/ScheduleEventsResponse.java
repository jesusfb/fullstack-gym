package com.backend.response;

public class ScheduleEventsResponse
{
    private int course_id;
    private String Day;
    private String StartDateTime;
    private long StartTimeInEpochSeconds;
    private String EndDateTime;
    private long EndTimeInEpochSeconds;
    private String Room;
    private String Course_name;

    public ScheduleEventsResponse() {
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getStartDateTime() {
        return StartDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        StartDateTime = startDateTime;
    }

    public long getStartTimeInEpochSeconds() {
        return StartTimeInEpochSeconds;
    }

    public void setStartTimeInEpochSeconds(long startTimeInEpochSeconds) {
        StartTimeInEpochSeconds = startTimeInEpochSeconds;
    }

    public String getEndDateTime() {
        return EndDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        EndDateTime = endDateTime;
    }

    public long getEndTimeInEpochSeconds() {
        return EndTimeInEpochSeconds;
    }

    public void setEndTimeInEpochSeconds(long endTimeInEpochSeconds) {
        EndTimeInEpochSeconds = endTimeInEpochSeconds;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getCourse_name() {
        return Course_name;
    }

    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}