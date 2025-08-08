package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalStockResponse;
import com.mohsin.integratehub.dto.StockSearchResult;
import com.mohsin.integratehub.dto.AlphaQuoteResponse;
import com.mohsin.integratehub.dto.AlphaSearchMatch;
import com.mohsin.integratehub.dto.AlphaSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StockClient {

    private static final Logger log = LoggerFactory.getLogger(StockClient.class);

    private final WebClient webClient;
    private final String apiKey;

    public StockClient(WebClient.Builder builder,
                       @Value("${integrations.stocks.base-url:https://www.alphavantage.co}") String baseUrl,
                       @Value("${integrations.stocks.api-key:demo}") String apiKey) {

        this.webClient = builder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public ExternalStockResponse getQuote(String symbol) {
        try {
            log.info("Calling stock API for symbol={}", symbol);
            AlphaQuoteResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/query")
                            .queryParam("function", "GLOBAL_QUOTE")
                            .queryParam("symbol", symbol)
                            .queryParam("apikey", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(AlphaQuoteResponse.class)
                    .block();

            AlphaQuoteResponse.GlobalQuote quote = response != null ? response.getGlobalQuote() : null;

            if (quote == null || quote.getSymbol() == null) {
                throw new StockClientException("No quote found for symbol: " + symbol);
            }

            return new ExternalStockResponse(
                    quote.getSymbol(),
                    parseDouble(quote.getPrice(), "price"),
                    parseDouble(quote.getOpen(), "open"),
                    parseDouble(quote.getHigh(), "high"),
                    parseDouble(quote.getLow(), "low"),
                    parseDouble(quote.getPreviousClose(), "previous close")
            );
        } catch (WebClientResponseException ex) {
            log.error("Stock API error for {}: status={} body={}",
                    symbol, ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new StockClientException("Error fetching stock quote", ex);
        } catch (Exception ex) {
            log.error("Unexpected stock error", ex);
            throw new StockClientException("Failed to call stock API", ex);
        }
    }

    public List<StockSearchResult> search(String query) {
        try {
            log.info("Searching stocks for query={}", query);
            AlphaSearchResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/query")
                            .queryParam("function", "SYMBOL_SEARCH")
                            .queryParam("keywords", query)
                            .queryParam("apikey", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(AlphaSearchResponse.class)
                    .block();

            List<AlphaSearchMatch> matches = response != null
                    ? Objects.requireNonNullElse(response.getBestMatches(), Collections.emptyList())
                    : Collections.emptyList();

            return matches.stream()
                    .filter(result -> result.getSymbol() != null)
                    .map(result -> new StockSearchResult(
                            result.getSymbol(),
                            result.getName(),
                            null,
                            result.getRegion(),
                            result.getType()))
                    .collect(Collectors.toList());
        } catch (WebClientResponseException ex) {
            log.error("Stock search API error for {}: status={} body={}",
                    query, ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new StockClientException("Error searching for stocks", ex);
        } catch (Exception ex) {
            log.error("Unexpected stock search error", ex);
            throw new StockClientException("Failed to search for stocks", ex);
        }
    }

    private double parseDouble(String value, String field) {
        try {
            return Double.parseDouble(value);
        } catch (Exception ex) {
            throw new StockClientException("Invalid " + field + " returned by stock API", ex);
        }
    }
}