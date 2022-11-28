package com.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private int user_id;

    @Column(name = "user_name",nullable = false)
    private String user_name; // admin or simple

    @Column(name = "user_lastname",nullable = false)
    private String user_lastname;

    @Column(name = "user_type",nullable = false)
    private String user_type; // admin or simple

    @Column(name = "user_email",nullable = false)
    private String user_email;

    @Column(name = "user_address",nullable = false)
    private String user_address;

    @Column(name = "user_adt",nullable = false)
    private String user_adt;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_courses", // book_author
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    private List<Course> courses;

    public User(int user_id, String user_name, String user_lastname, String user_type, String user_email, String user_address, String user_adt, Plan plan, List<Course> courses) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.user_type = user_type;
        this.user_email = user_email;
        this.user_address = user_address;
        this.user_adt = user_adt;
        this.plan = plan;
        this.courses = courses;
    }

    public User()
    {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_adt() {
        return user_adt;
    }

    public void setUser_adt(String user_adt) {
        this.user_adt = user_adt;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}