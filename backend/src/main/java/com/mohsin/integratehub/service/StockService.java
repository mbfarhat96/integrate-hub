package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalStockResponse;
import com.mohsin.integratehub.dto.StockQuoteResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class StockService {

    private final StockClient stockClient;

    public StockService(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @Cacheable(cacheNames = "stockQuotes")
    public StockQuoteResponse getQuote(String symbol) {
        String normalized = normalize(symbol);

        ExternalStockResponse ext = stockClient.getQuote(normalized);

        double price = ext.getPrice();
        double prev = ext.getPreviousClose();

        double percentChange = ((price - prev) / prev) * 100.0;

        return new StockQuoteResponse(
                ext.getSymbol(),
                price,
                ext.getOpen(),
                ext.getHigh(),
                ext.getLow(),
                ext.getPreviousClose(),
                round(percentChange, 2),
                LocalDateTime.now()
        );
    }

    private String normalize(String s) {
        return s.trim().toUpperCase(Locale.ROOT);
    }

    private double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}
