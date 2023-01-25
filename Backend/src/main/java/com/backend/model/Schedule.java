package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule
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
}