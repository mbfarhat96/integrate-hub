package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalNewsResponse;
import com.mohsin.integratehub.service.NewsClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class NewsClient {

    private static final Logger log = LoggerFactory.getLogger(NewsClient.class);

    private final WebClient webClient;
    private final String baseUrl;
    private final String apiKey;

    public NewsClient(WebClient.Builder builder,
                      @Value("${integrations.news.base-url:https://gnews.io/api/v4}") String baseUrl,
                      @Value("${integrations.news.api-key:demo}") String apiKey) {

        this.webClient = builder.baseUrl(baseUrl).build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ExternalNewsResponse searchNews(String query) {
        try {
            log.info("Calling News API for query={}", query);
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search")
                            .queryParam("q", query)
                            .queryParam("lang", "en")
                            .queryParam("max", 20)
                            .queryParam("token", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(ExternalNewsResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("News API error for query={}: status={} body={}",
                    query, ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new NewsClientException("Error fetching news results", ex);
        } catch (Exception ex) {
            log.error("Unexpected News API error", ex);
            throw new NewsClientException("Failed to call News API", ex);
        }
    }
}