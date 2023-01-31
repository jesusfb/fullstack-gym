package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id",nullable = false)
    private int id;

    @Column(name = "plan_type",nullable = false)
    private String plan_type;

    @Column(name = "plan_description",nullable = false)
    private String plan_desc;

    @Column(name = "plan_duration",nullable = false)
    private int plan_duration;

    @Column(name = "plan_price",nullable = false)
    private float plan_price;

    @Column(name = "image_url")
    private String image_url = "";

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "plans_courses",
            joinColumns = { @JoinColumn(name = "plan_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private Set<Course> courseSet = new HashSet<>();


    public Plan(String plan_type, String plan_desc, int plan_duration, float plan_price)
    {
        this.plan_type = plan_type;
        this.plan_desc = plan_desc;
        this.plan_duration = plan_duration;
        this.plan_price = plan_price;
    }

    public void addCourse(Course course)
    {
        this.courseSet.add(course);
        course.getPlanSet().add(this);
    }

    public void removeCourse(int course_id) {
        Course course = this.courseSet.stream().filter(c -> c.getId() == course_id).findFirst().orElse(null);
        if (course != null)
        {
            this.courseSet.remove(course);
            course.getPlanSet().remove(this);
        }
    }
}