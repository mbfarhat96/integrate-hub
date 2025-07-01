package com.mohsin.integratehub.dto;

import java.time.LocalDateTime;

public class CurrencyConversionResponse {

    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private double amount;
    private double convertedAmount;
    private LocalDateTime fetchedAt;

    public CurrencyConversionResponse() {
    }

    public CurrencyConversionResponse(String fromCurrency,
                                      String toCurrency,
                                      double rate,
                                      double amount,
                                      double convertedAmount,
                                      LocalDateTime fetchedAt) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.fetchedAt = fetchedAt;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }
}
