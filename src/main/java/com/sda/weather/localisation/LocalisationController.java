package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationService localisationService;

    @GetMapping("/localisation")
    LocalisationDTO getLocalisations(){

    }

    @PostMapping("/localisation")
    LocalisationDTO createLocalisation(){


    }


}
