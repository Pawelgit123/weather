package com.sda.weather.forecast;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepository extends JpaRepository<ForecastData, Long> {
}
