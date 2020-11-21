package com.sda.weather.localisation;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalisationServiceCreateTest {

    @Autowired
    LocalisationRepository localisationRepository;

    @BeforeEach
    void setup(){
        localisationRepository.deleteAll();
    }

}