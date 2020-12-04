package com.sda.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastItem {

    @JsonProperty("id")
    int id;
    @JsonProperty("temp")
    int airTemperature;
    @JsonProperty("pressure")
    int airPressure;
    @JsonProperty("humidity")
    int airHumidity;
    @JsonProperty("wind_dir")
    String windDirection;
    @JsonProperty("wind_speed")
    int windSpeed;

}

// todo your JSON (http://api.openweathermap.org/data/2.5/find?q=Gdansk&units=metric&appid=a5bd02ecf7c1f72449ae4d087d08d275):
/*
{
  "message": "accurate",        // String message
  "cod": "200",                 // String cod
  "count": 3,                   // int count
  "list": [                     // List<NewObject> list  (NewObject - you must create and name a new class)
    {
      "id": 3099434,            // inside NewObject - int id
      "name": "Gdańsk",         // inside NewObject - String name
      "coord": {                // inside NewObject - CoordinatesObject (CoordinatesObject - you must create and name a new class)
        "lat": 54.3521,         // inside CoordinatesObject - float lat
        "lon": 18.6464          // inside CoordinatesObject - float lon
      },                        // ...
      "main": {
        "temp": 1.78,
        "feels_like": -5.19,
        "temp_min": 1.67,
        "temp_max": 2,
        "pressure": 1001,
        "humidity": 75
      },
      "dt": 1607094590,
      "wind": {
        "speed": 6.7,
        "deg": 140
      },
      "sys": {
        "country": "PL"
      },
      "rain": null,
      "snow": null,
      "clouds": {
        "all": 20
      },
      "weather": [
        {
          "id": 801,
          "main": "Clouds",
          "description": "few clouds",
          "icon": "02n"
        }
      ]
    },
    {
      "id": 7531002,
      "name": "Gdańsk",
      "coord": {
        "lat": 54.3611,
        "lon": 18.6898,

        ...
 */

