package com.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id",nullable = false)
    private int course_id;

    @Column(name = "course_name",nullable = false)
    private String course_name;

    @Column(name = "course_description",nullable = false)
    private String course_description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(mappedBy = "courses")
    private List<Plan> plans;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    public Course()
    {

    }

    public Course(int course_id, String course_name, Instructor instructor, List<Plan> plans, List<User> users,String course_description)
    {
        this.course_id = course_id;
        this.course_name = course_name;
        this.instructor = instructor;
        this.plans = plans;
        this.users = users;
        this.course_description = course_description;
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

    public List<Plan> getPlans()
    {
        return plans;
    }

    public void setPlans(List<Plan> plans)
    {
        this.plans = plans;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public String getCourse_description()
    {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }
}