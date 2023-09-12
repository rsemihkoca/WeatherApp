package com.project.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse
        (
                Request request,
                Location location,
                Current current
        ) {}
