package com.backend.repository;

import com.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>
{
    List<User> findByPlan_Id(int id);
    Optional<User> findByEmail(String email);
}
