package com.sda.weather.forecast;

import com.sda.weather.Configuration;
import com.sda.weather.exceptions.NotFoundException;
import com.sda.weather.localisation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class ForecastController {

    final ForecastServiceGet forecastServiceGet;
    final ForecastDataMapper forecastDataMapper;
    final Configuration configuration;

    @GetMapping("/localisation/{id}/forecast")
    ForecastDataDTO getForecast(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(5) String period) {
        ForecastData forecastData = forecastServiceGet.getForecast(id, period);
        return forecastDataMapper.mapForecastDataToForecastDataDTO(forecastData);
    }
}



