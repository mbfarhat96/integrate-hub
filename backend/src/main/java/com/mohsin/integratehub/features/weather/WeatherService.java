package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalWeatherResponse;
import com.mohsin.integratehub.dto.GeocodingResult;
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

        GeocodingResult geocodingResult = geocodingClient.getCoordinatesForCity(city);
        double lat = geocodingResult.getLatitude();
        double lon = geocodingResult.getLongitude();

        ExternalWeatherResponse external = weatherClient.getWeatherByCoordinates(lat, lon);

        return new WeatherResponse(
                city,
                geocodingResult.getCountry(),
                external.getTemp(),
                external.getFeelsLike(),
                external.getHumidity(),
                external.getDescription(),
                LocalDateTime.now()
        );
    }
}