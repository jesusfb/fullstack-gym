package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private int user_id;

    @Column(name = "user_name",nullable = false)
    private String user_name;

    @Column(name = "user_lastname",nullable = false)
    private String user_lastname;

    @Column(name = "user_type",nullable = false)
    private String user_type;

    @Column(name = "user_email",nullable = false)
    private String user_email;

    @Column(name = "user_address",nullable = false)
    private String user_address;

    @Column(name = "image_url")
    private String image_url = "";

    @Column(name = "registered_date")
    private LocalDate registered_date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Transient
    private Integer plan_id;
}