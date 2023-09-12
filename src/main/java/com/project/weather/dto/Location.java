package com.project.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
        String name,
        String country,
        String region,

        String lat,
        String lon,
        @JsonProperty("tz_id")
        @JsonProperty("localtime")
        String localTime
) {
}

