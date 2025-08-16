package com.mohsin.integratehub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalStockResponse {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("price")
    private double price;

    @JsonProperty("open")
    private double open;

    @JsonProperty("high")
    private double high;

    @JsonProperty("low")
    private double low;

    @JsonProperty("previous_close")
    private double previousClose;

    public ExternalStockResponse() {
    }

    public ExternalStockResponse(String symbol, double price, double open, double high,
                                 double low, double previousClose) {
        this.symbol = symbol;
        this.price = price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.previousClose = previousClose;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }
}
