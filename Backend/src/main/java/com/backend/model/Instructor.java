package com.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "instructors")
public class Instructor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id",nullable = false)
    private int id;

    @Column(name = "instructor_name",nullable = false)
    private String instructor_name;

    @Column(name = "instructor_lastname",nullable = false)
    private String instructor_lastname;

    @Column(name = "instructor_email",nullable = false)
    private String instructor_email;

    @Column(name = "instructor_specialty",nullable = false)
    private String instructor_specialty;

    @Column(name = "image_url")
    private String image_url = "";

    public Instructor()
    {

    }

    public Instructor(String instructor_name, String instructor_lastname, String instructor_email,String instructor_specialty)
    {
        this.instructor_name = instructor_name;
        this.instructor_lastname = instructor_lastname;
        this.instructor_email = instructor_email;
        this.instructor_specialty=instructor_specialty;
    }

    public String getImage_url()
    {
        return image_url;
    }

    public void setImage_url(String image_url)
    {
        this.image_url = image_url;
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

    public String getInstructor_specialty() {
        return instructor_specialty;
    }

    public void setInstructor_specialty(String instructor_specialty) {
        this.instructor_specialty = instructor_specialty;
    }
}
