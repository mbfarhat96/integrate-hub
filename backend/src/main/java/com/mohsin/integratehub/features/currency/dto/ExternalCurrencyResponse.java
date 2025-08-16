package com.mohsin.integratehub.dto;

import java.util.Map;

public class ExternalCurrencyResponse {

    private String base;
    private String date;
    private Map<String, Double> rates;

    public ExternalCurrencyResponse() {
    }

    public ExternalCurrencyResponse(String base, String date, Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
