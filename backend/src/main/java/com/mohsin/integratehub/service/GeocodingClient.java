package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.GeocodingResult;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class GeocodingClient {

    private final WebClient webClient;

    public GeocodingClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public GeocodingResult getCoordinatesForCity(String city) {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + city;

        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // FIX: replace var with explicit type
        List<Map<String, Object>> results =
                (List<Map<String, Object>>) response.get("results");

        if (results == null || results.isEmpty()) {
            throw new RuntimeException("City not found: " + city);
        }

        Map<String, Object> first = results.get(0);

        double lat = ((Number) first.get("latitude")).doubleValue();
        double lon = ((Number) first.get("longitude")).doubleValue();
        String country = (String) first.getOrDefault("country", "");

        return new GeocodingResult(lat, lon, country);
    }

}
