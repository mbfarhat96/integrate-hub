package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalCurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CurrencyClient {

    private static final Logger log = LoggerFactory.getLogger(CurrencyClient.class);

    private final WebClient webClient;
    private final String baseUrl;
    private final String apiKey;

    public CurrencyClient(WebClient.Builder builder,
                          @Value("${integrations.currency.base-url:http://api.exchangeratesapi.io/v1}") String baseUrl,
                          @Value("${integrations.currency.api-key:}") String apiKey) {
        this.webClient = builder.build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ExternalCurrencyResponse getRates(String fromCurrency, String toCurrency) {
        try {
            log.info("Calling currency API for from={} to={}", fromCurrency, toCurrency);

            String symbols = Stream.of(fromCurrency, toCurrency)
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .distinct()
                    .collect(Collectors.joining(","));

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .path("/latest")
                    .queryParamIfPresent("access_key", apiKey == null || apiKey.isBlank() ? Optional.empty() : Optional.of(apiKey));

            if (!symbols.isEmpty()) {
                uriBuilder.queryParam("symbols", symbols);
            }

            String url = uriBuilder.toUriString();

            ExternalCurrencyResponse response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(ExternalCurrencyResponse.class)
                    .block();

            if (response == null || response.getRates() == null) {
                throw new CurrencyClientException("Currency API returned no rates");
            }

            return response;
        } catch (WebClientResponseException ex) {
            log.error("Currency API error for from={} to={}: status={} body={}",
                    fromCurrency, toCurrency, ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new CurrencyClientException("Error fetching currency rates", ex);
        } catch (Exception ex) {
            log.error("Unexpected error calling currency API", ex);
            throw new CurrencyClientException("Failed to call currency API", ex);
        }
    }
}