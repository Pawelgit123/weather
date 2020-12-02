package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ForecastServiceUpdate {

    void  updateForecastDateListForLocalisation (LocalisationDTO localisationDTO, Localisation localisation){

        localisation.setForecastDataList(localisationDTO.getForecastDataList());

    }
}
