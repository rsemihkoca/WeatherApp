package com.project.weather.dto;

import com.project.weather.model.WeatherEntity;

public record WeatherDto (
    String cityName,
    String country,
    Integer temperature
) {
    static public WeatherDto convert(WeatherEntity from) {
        return new WeatherDto(from.getCityName(), from.getCountry(), from.getTemperature());
    }


}
