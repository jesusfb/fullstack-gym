package com.backend.repository;

import com.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer>
{
    Optional<Image> findByName(String name);
}