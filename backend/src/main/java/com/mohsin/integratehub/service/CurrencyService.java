package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.CurrencyConversionResponse;
import com.mohsin.integratehub.dto.ExternalCurrencyResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

@Service
public class CurrencyService {

    private final CurrencyClient currencyClient;

    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Cacheable(cacheNames = "currencyRates")
    public CurrencyConversionResponse convert(String from, String to, double amount) {
        String base = normalizeCode(from);
        String target = normalizeCode(to);

        ExternalCurrencyResponse external = currencyClient.getRates(base);
        Map<String, Double> rates = external.getRates();

        if (rates == null || !rates.containsKey(target)) {
            throw new IllegalArgumentException("No rate available for target currency: " + target);
        }

        double rate = rates.get(target);
        double converted = amount * rate;

        return new CurrencyConversionResponse(
                base,
                target,
                rate,
                amount,
                converted,
                LocalDateTime.now()
        );
    }

    private String normalizeCode(String code) {
        if (code == null) {
            return null;
        }
        return code.trim().toUpperCase(Locale.ROOT);
    }
}
