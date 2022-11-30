package com.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "instructors")
public class Instructor
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_generator")
    @Column(name = "instructor_id",nullable = false)
    private int id;

    @Column(name = "instructor_name",nullable = false)
    private String instructor_name;

    @Column(name = "instructor_lastname",nullable = false)
    private String instructor_lastname;

    @Column(name = "instructor_email",nullable = false)
    private String instructor_email;

    public Instructor()
    {

    }

    public Instructor(String instructor_name, String instructor_lastname, String instructor_email)
    {
        this.instructor_name = instructor_name;
        this.instructor_lastname = instructor_lastname;
        this.instructor_email = instructor_email;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int instructor_id)
    {
        this.id = instructor_id;
    }

    public String getInstructor_name()
    {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name)
    {
        this.instructor_name = instructor_name;
    }

    public String getInstructor_lastname()
    {
        return instructor_lastname;
    }

    public void setInstructor_lastname(String instructor_lastname)
    {
        this.instructor_lastname = instructor_lastname;
    }

    public String getInstructor_email()
    {
        return instructor_email;
    }

    public void setInstructor_email(String instructor_email)
    {
        this.instructor_email = instructor_email;
    }
}
