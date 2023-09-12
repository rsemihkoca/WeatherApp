package com.project.weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
/*
    Data model for weather entity
*/
@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID")
    private String id;
    private String requestedCityName;
    private String cityName;
    private String country;
    private Integer temperature;
    private LocalDateTime updatedAt;
    private LocalDateTime responseLocalTime;

    // Spring Data JPA needs 3 constructors: default, with id, without id
    public WeatherEntity(String id, String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedAt, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedAt = updatedAt;
        this.responseLocalTime = responseLocalTime;
    }

    // Constructor for creating new entity without id
    public WeatherEntity(String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedAt, LocalDateTime responseLocalTime) {
    this.id = "";
    this.requestedCityName = requestedCityName;
    this.cityName = cityName;
    this.country = country;
    this.temperature = temperature;
    this.updatedAt = updatedAt;
    this.responseLocalTime = responseLocalTime;
    }
    public WeatherEntity() {
    }

    public String getId() {
        return id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }
}
