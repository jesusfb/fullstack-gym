package com.backend.repository;

import com.backend.model.Image;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;
    private Image image;

    @BeforeEach
    void setUp() {
        image = new Image(1,"image.png","png","[url]");
        imageRepository.save(image);
    }

    @AfterEach
    void tearDown()
    {
        image = null;
        imageRepository.deleteAll();
    }

    // Test Case SUCCESS
    @Test
    void findByName_FOUND()
    {
        Optional<Image> tested_image = imageRepository.findByName("image.png");
        assertThat(tested_image.get().getId()).isEqualTo(image.getId());
    }

    // Test Case FAILURE
    @Test
    void findByName_NOTFOUND()
    {
        Optional<Image> tested_image = imageRepository.findByName("other.png");
        assertThat(tested_image.isEmpty()).isTrue();
    }
}