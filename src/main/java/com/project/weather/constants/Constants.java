package com.project.weather.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    public static String API_URL;
    @Value("${weather-stack.api-url}")
    public static void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }
    //

}
