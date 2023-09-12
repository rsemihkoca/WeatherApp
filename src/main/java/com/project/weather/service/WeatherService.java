package com.project.weather.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.weather.dto.WeatherDto;
import com.project.weather.dto.WeatherResponse;
import com.project.weather.model.WeatherEntity;
import com.project.weather.repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    private final RestTemplate restTemplate;

    public WeatherService(WeatherRepository weatherRepository, RestTemplate restTemplate) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = restTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper(); // for converting JSON to Java object

    public WeatherDto getWeatherByCityName(String city) {

        //Returned value can be null, so we need to use Optional
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(city);

        //If we have weatherEntityOptional, we can use it, otherwise we need to call API
        if (!weatherEntityOptional.isPresent()) {
            return WeatherDto.convert(getWeatherFromWeatherStack(city));
        }

        return WeatherDto.convert(weatherEntityOptional.get());

    }

    private WeatherEntity getWeatherFromWeatherStack(String city) {
        String url = "http://api.weatherstack.com/current?access_key=645749b5550ea09a8814a4a0cfbe3f8d&query=" + city;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        try {
            WeatherResponse weatherResponse = objectMapper.readValue(responseEntity.getBody(), WeatherResponse.class);
            return saveWeatherEntity(city, weatherResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        WeatherEntity weatherEntity = new WeatherEntity(
                city,
                weatherResponse.location().name(),
                weatherResponse.location().country(),
                weatherResponse.current().temperature(),
                LocalDateTime.now(),
                LocalDateTime.parse(weatherResponse.location().localTime(), formatter));

        return weatherRepository.save(weatherEntity);
    }
}
