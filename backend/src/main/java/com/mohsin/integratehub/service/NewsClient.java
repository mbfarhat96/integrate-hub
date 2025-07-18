package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalNewsResponse;
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
                      @Value("${integrations.news.base-url:https://api.example-news.com}") String baseUrl,
                      @Value("${integrations.news.api-key:demo-key}") String apiKey) {

        this.webClient = builder.build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ExternalNewsResponse searchNews(String query) {
        try {
            log.info("Calling News API for query={}", query);
            return webClient.get()
                    .uri(baseUrl + "/search?q=" + query + "&apikey=" + apiKey)
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
