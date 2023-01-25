package com.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "instructors")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Instructor(String instructor_name, String instructor_lastname, String instructor_email, String instructor_specialty)
    {
        this.instructor_name = instructor_name;
        this.instructor_lastname = instructor_lastname;
        this.instructor_email = instructor_email;
        this.instructor_specialty = instructor_specialty;
    }
}
