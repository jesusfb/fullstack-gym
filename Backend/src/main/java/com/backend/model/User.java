package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "firstname")
    private String user_name;

    @Column(name = "lastname")
    private String user_lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "address")
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

    public User(int user_id, String user_name, String user_lastname, Role role, String email, String password, String user_address, String image_url, LocalDate registered_date) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_lastname = user_lastname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.user_address = user_address;
        this.image_url = image_url;
        this.registered_date = registered_date;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}