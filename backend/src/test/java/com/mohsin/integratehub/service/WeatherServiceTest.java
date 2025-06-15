package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalWeatherResponse;
import com.mohsin.integratehub.dto.WeatherResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Test
    void getWeatherForCity_shouldMapExternalResponseToInternalModel() {
        WeatherClient weatherClient = mock(WeatherClient.class);

        ExternalWeatherResponse external = new ExternalWeatherResponse(
                "Chicago",
                "US",
                20.5,
                19.0,
                60,
                "Clear sky"
        );

        when(weatherClient.getWeatherByCity("Chicago")).thenReturn(external);

        WeatherService weatherService = new WeatherService(weatherClient);

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
