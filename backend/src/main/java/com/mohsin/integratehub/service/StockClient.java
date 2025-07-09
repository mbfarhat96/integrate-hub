package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalStockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class StockClient {

    private static final Logger log = LoggerFactory.getLogger(StockClient.class);

    private final WebClient webClient;
    private final String baseUrl;
    private final String apiKey;

    public StockClient(WebClient.Builder builder,
                       @Value("${integrations.stocks.base-url:https://api.example-stocks.com}") String baseUrl,
                       @Value("${integrations.stocks.api-key:demo-key}") String apiKey) {

        this.webClient = builder.build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ExternalStockResponse getQuote(String symbol) {
        try {
            log.info("Calling stock API for symbol={}", symbol);
            return webClient.get()
                    .uri(baseUrl + "/quote?symbol=" + symbol + "&apikey=" + apiKey)
                    .retrieve()
                    .bodyToMono(ExternalStockResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Stock API error for {}: status={} body={}",
                    symbol, ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new StockClientException("Error fetching stock quote", ex);
        } catch (Exception ex) {
            log.error("Unexpected stock error", ex);
            throw new StockClientException("Failed to call stock API", ex);
        }
    }
}
