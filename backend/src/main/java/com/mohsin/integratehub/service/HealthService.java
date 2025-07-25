package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.SystemHealthResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HealthService {

    private final WeatherService weather;
    private final GitHubService github;
    private final CurrencyService currency;
    private final StockService stocks;
    private final NewsService news;

    public HealthService(WeatherService weather,
                         GitHubService github,
                         CurrencyService currency,
                         StockService stocks,
                         NewsService news) {
        this.weather = weather;
        this.github = github;
        this.currency = currency;
        this.stocks = stocks;
        this.news = news;
    }

    public SystemHealthResponse getSystemHealth() {

        Map<String, String> statuses = new LinkedHashMap<>();

        statuses.put("weather", "OK");
        statuses.put("github", "OK");
        statuses.put("currency", "OK");
        statuses.put("stocks", "OK");
        statuses.put("news", "OK");

        return new SystemHealthResponse(
                true,
                LocalDateTime.now(),
                statuses,
                "1.0.0"
        );
    }
}
