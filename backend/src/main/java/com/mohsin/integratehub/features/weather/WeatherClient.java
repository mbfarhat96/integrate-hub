package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Component
public class WeatherClient {

    private final WebClient webClient;

    @Value("${integrations.weather.base-url}")
    private String baseUrl;

    public WeatherClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public ExternalWeatherResponse getWeatherByCoordinates(double lat, double lon) {

        String url = String.format(
                "%s?latitude=%f&longitude=%f&current_weather=true",
                baseUrl, lat, lon);

        Map<String, Object> response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> current = (Map<String, Object>) response.get("current_weather");

        return new ExternalWeatherResponse(
                null,
                null,
                (double) current.get("temperature"),
                (double) current.get("temperature"),
                0,
                String.valueOf(current.get("weathercode"))
        );
    }

}
