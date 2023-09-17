package com.project.weather.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Open Weather API")
                        .version("1.0.0")
                        .description("""
                                This is a sample Spring Boot RESTful service using springdoc-openapi and Open Weather API.
                                For last 30 minutes, the service is limited to 10 requests per minute.
                                You can find out more about springdoc-openapi at
                                [https://springdoc.org/](https://springdoc.org/)."""));
    }
}
