package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.WeatherResponse;
import com.mohsin.integratehub.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public ResponseEntity<WeatherResponse> getWeather(
            @RequestParam("city") @NotBlank String city
    ) {
        WeatherResponse response = weatherService.getWeatherForCity(city);
        return ResponseEntity.ok(response);
    }
}
