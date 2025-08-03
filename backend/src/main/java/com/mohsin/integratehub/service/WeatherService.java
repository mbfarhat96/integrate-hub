package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalWeatherResponse;
import com.mohsin.integratehub.dto.WeatherResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WeatherService {

    private final WeatherClient weatherClient;
    private final GeocodingClient geocodingClient;

    public WeatherService(WeatherClient weatherClient,
                          GeocodingClient geocodingClient) {
        this.weatherClient = weatherClient;
        this.geocodingClient = geocodingClient;
    }

    @Cacheable(cacheNames = "weatherByCity")
    public WeatherResponse getWeatherForCity(String city) {

        double[] coords = geocodingClient.getCoordinatesForCity(city);
        double lat = coords[0];
        double lon = coords[1];

        ExternalWeatherResponse external = weatherClient.getWeatherByCoordinates(lat, lon);

        return new WeatherResponse(
                city,
                external.getCountry(),
                external.getTemp(),
                external.getFeelsLike(),
                external.getHumidity(),
                external.getDescription(),
                LocalDateTime.now()
        );
    }
}

