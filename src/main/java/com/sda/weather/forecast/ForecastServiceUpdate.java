package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationDTO;

import java.util.List;

public class ForecastServiceUpdate {

    void  updateForecastDateListForLocalisation (LocalisationDTO localisationDTO, Localisation localisation){

        localisation.setForecastDataList(localisationDTO.getForecastDataList());

    }
}
