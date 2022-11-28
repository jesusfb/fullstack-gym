package com.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plans")
public class Plan
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id",nullable = false)
    private int plan_id;

    @Column(name = "plan_type",nullable = false)
    private String plan_type;

    @Column(name = "plan_desc",nullable = false)
    private String plan_desc;

    @Column(name = "plan_duration",nullable = false)
    private int plan_duration;

    @Column(name = "plan_price",nullable = false)
    private float plan_price;

    @OneToMany(mappedBy = "plan" )
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "plan_courses", // book_author
            joinColumns = @JoinColumn(name = "plan_id", referencedColumnName = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private List<Course> courses;

    public Plan() {
    }

    public Plan(int plan_id, String plan_type, String plan_desc, int plan_duration, float plan_price, List<User> users, List<Course> courses) {
        this.plan_id = plan_id;
        this.plan_type = plan_type;
        this.plan_desc = plan_desc;
        this.plan_duration = plan_duration;
        this.plan_price = plan_price;
        this.users = users;
        this.courses = courses;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getPlan_desc() {
        return plan_desc;
    }

    public void setPlan_desc(String plan_desc) {
        this.plan_desc = plan_desc;
    }

    public int getPlan_duration() {
        return plan_duration;
    }

    public void setPlan_duration(int plan_duration) {
        this.plan_duration = plan_duration;
    }

    public float getPlan_price() {
        return plan_price;
    }

    public void setPlan_price(float plan_price) {
        this.plan_price = plan_price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}