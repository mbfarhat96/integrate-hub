package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalCurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class CurrencyClient {

    private static final Logger log = LoggerFactory.getLogger(CurrencyClient.class);

    private final WebClient webClient;
    private final String baseUrl;
    private final String apiKey;

    public CurrencyClient(WebClient.Builder builder,
                          @Value("${integrations.currency.base-url:https://api.example-currency.com}") String baseUrl,
                          @Value("${integrations.currency.api-key:demo-key}") String apiKey) {
        this.webClient = builder.build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public ExternalCurrencyResponse getRates(String base) {
        try {
            log.info("Calling currency API for base={}", base);
            return webClient.get()
                    .uri(baseUrl + "/latest?base=" + base + "&apikey=" + apiKey)
                    .retrieve()
                    .bodyToMono(ExternalCurrencyResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Currency API error for base={}: status={} body={}",
                    base, ex.getRawStatusCode(), ex.getResponseBodyAsString());
            throw new CurrencyClientException("Error fetching currency rates", ex);
        } catch (Exception ex) {
            log.error("Unexpected error calling currency API", ex);
            throw new CurrencyClientException("Failed to call currency API", ex);
        }
    }
}
