package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class WeatherClient {

    private static final Logger log = LoggerFactory.getLogger(WeatherClient.class);

    private final WebClient webClient;
    private final String baseUrl;
    private final String apiKey;

    public WeatherClient(WebClient.Builder webClientBuilder,
                         @Value("${integrations.weather.base-url:https://api.example-weather.com}") String baseUrl,
                         @Value("${integrations.weather.api-key:demo-key}") String apiKey) {
        this.webClient = webClientBuilder.build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ExternalWeatherResponse getWeatherByCity(String city) {
        try {
            return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host(baseUrl.replace("https://", "").replace("http://", ""))
                            .path("/weather")
                            .queryParam("q", city)
                            .queryParam("units", "metric")
                            .queryParam("appid", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(ExternalWeatherResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Weather API error: status={}, body={}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new WeatherClientException("Weather API returned an error response", ex);
        } catch (Exception ex) {
            log.error("Unexpected error calling weather API", ex);
            throw new WeatherClientException("Failed to call weather API", ex);
        }
    }
}
