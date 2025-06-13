package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalWeatherResponse;
import com.mohsin.integratehub.dto.WeatherResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }
    @Cacheable(cacheNames = "weatherByCity")
    public WeatherResponse getWeatherForCity(String city) {
        ExternalWeatherResponse external = weatherClient.getWeatherByCity(city);

        if (external == null) {
            // Simple fallback – in a real app you might throw a custom exception
            return new WeatherResponse(
                    city,
                    null,
                    0.0,
                    0.0,
                    0,
                    "No data available",
                    LocalDateTime.now()
            );
        }

        return new WeatherResponse(
                external.getName(),
                external.getCountry(),
                external.getTemp(),
                external.getFeelsLike(),
                external.getHumidity(),
                external.getDescription(),
                LocalDateTime.now()
        );
    }
}
