package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "schedule_events")
public class ScheduleEvents
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id",nullable = false)
    private int schedule_id;

    @Column(name = "scheduled_day",nullable = false)
    private String scheduled_day;

    @Column(name = "scheduled_start_time",nullable = false)
    private String scheduled_start_time;

    @Column(name = "scheduled_end_time",nullable = false)
    private String scheduled_end_time;

    @Column(name = "scheduled_room",nullable = false)
    private String scheduled_room;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @Transient
    private String scheduled_course;

    public ScheduleEvents() {

    }

    public ScheduleEvents(String scheduled_day, String scheduled_start_time, String scheduled_end_time, String scheduled_room) {
        this.scheduled_day = scheduled_day;
        this.scheduled_start_time = scheduled_start_time;
        this.scheduled_end_time = scheduled_end_time;
        this.scheduled_room = scheduled_room;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getScheduled_day() {
        return scheduled_day;
    }

    public void setScheduled_day(String scheduled_day) {
        this.scheduled_day = scheduled_day;
    }

    public String getScheduled_start_time() {
        return scheduled_start_time;
    }

    public void setScheduled_start_time(String scheduled_start_time) {
        this.scheduled_start_time = scheduled_start_time;
    }

    public String getScheduled_end_time() {
        return scheduled_end_time;
    }

    public void setScheduled_end_time(String scheduled_end_time) {
        this.scheduled_end_time = scheduled_end_time;
    }

    public String getScheduled_room() {
        return scheduled_room;
    }

    public void setScheduled_room(String scheduled_room) {
        this.scheduled_room = scheduled_room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getScheduled_course() {
        return scheduled_course;
    }

    public void setScheduled_course(String scheduled_course) {
        this.scheduled_course = scheduled_course;
    }
}