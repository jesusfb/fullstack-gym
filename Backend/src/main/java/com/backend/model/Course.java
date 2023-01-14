package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_generator")
    @Column(name = "course_id",nullable = false)
    private int course_id;

    @Column(name = "course_name",nullable = false)
    private String course_name;

    @Column(name = "course_description",nullable = false)
    private String course_description;

    @Column(name = "start_time",nullable = false)
    private int start_time;

    @Column(name = "end_time",nullable = false)
    private int end_time;

    @Column(name = "course_date",nullable = false)
    private String course_date;

    @Column(name = "image_url")
    private String image_url = "";

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "courseSet")
    @JsonIgnore
    private Set<Plan> planSet = new HashSet<>();

    @Transient
    private Integer[] plan_ids;

    @Transient
    private Integer instructor_id;

    public Course()
    {

    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Set<Plan> getPlanSet()
    {
        return planSet;
    }

    public void setPlanSet(Set<Plan> planSet)
    {
        this.planSet = planSet;
    }

    public int getCourse_id()
    {
        return course_id;
    }

    public void setCourse_id(int course_id)
    {
        this.course_id = course_id;
    }

    public String getCourse_name()
    {
        return course_name;
    }

    public void setCourse_name(String course_name)
    {
        this.course_name = course_name;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    public String getCourse_description()
    {
        return course_description;
    }

    public void setCourse_description(String course_description)
    {
        this.course_description = course_description;
    }

    public String getCourse_date() {
        return course_date;
    }

    public void setCourse_date(String course_date) {
        this.course_date = course_date;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public Integer[] getPlan_ids() {
        return plan_ids;
    }

    public void setPlan_ids(ArrayList<Integer> plan_ids) {
        this.plan_ids = plan_ids.toArray(new Integer[plan_ids.size()]);
    }

    public Integer getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(Integer instructor_id) {
        this.instructor_id = instructor_id;
    }

}