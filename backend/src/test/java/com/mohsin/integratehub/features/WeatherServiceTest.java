package com.mohsin.integratehub.service;

import com.mohsin.integratehub.features.weather.GeocodingClient;
import com.mohsin.integratehub.features.weather.WeatherClient;
import com.mohsin.integratehub.features.weather.WeatherService;
import com.mohsin.integratehub.features.weather.dto.ExternalWeatherResponse;
import com.mohsin.integratehub.features.weather.dto.GeocodingResult;
import com.mohsin.integratehub.features.weather.dto.WeatherResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Test
    void getWeatherForCity_shouldMapExternalResponseToInternalModel() {
        WeatherClient weatherClient = mock(WeatherClient.class);
        GeocodingClient geocodingClient = mock(GeocodingClient.class);

        ExternalWeatherResponse external = new ExternalWeatherResponse(
                "Chicago",
                "US",
                20.5,
                19.0,
                60,
                "Clear sky"
        );

        when(geocodingClient.getCoordinatesForCity("Chicago")).thenReturn(new GeocodingResult(41.8781, -87.6298, "US"));
        when(weatherClient.getWeatherByCoordinates(41.8781, -87.6298)).thenReturn(external);

        WeatherService weatherService = new WeatherService(weatherClient, geocodingClient);

        WeatherResponse result = weatherService.getWeatherForCity("Chicago");

        assertEquals("Chicago", result.getCity());
        assertEquals("US", result.getCountry());
        assertEquals(20.5, result.getTemperatureCelsius());
        assertEquals(19.0, result.getFeelsLikeCelsius());
        assertEquals(60, result.getHumidity());
        assertEquals("Clear sky", result.getDescription());
        assertNotNull(result.getFetchedAt());
    }
}