package com.mohsin.integratehub.dto;

import java.time.LocalDateTime;

public class StockQuoteResponse {

    private String symbol;
    private double price;
    private double open;
    private double high;
    private double low;
    private double previousClose;
    private double percentChange;
    private LocalDateTime fetchedAt;

    public StockQuoteResponse() {}

    public StockQuoteResponse(String symbol,
                              double price,
                              double open,
                              double high,
                              double low,
                              double previousClose,
                              double percentChange,
                              LocalDateTime fetchedAt) {
        this.symbol = symbol;
        this.price = price;
        this.open = open;
        this.high = high;
        this.low = low;
        this.previousClose = previousClose;
        this.percentChange = percentChange;
        this.fetchedAt = fetchedAt;
    }

    public String getSymbol() { return symbol; }
    public double getPrice() { return price; }
    public double getOpen() { return open; }
    public double getHigh() { return high; }
    public double getLow() { return low; }
    public double getPreviousClose() { return previousClose; }
    public double getPercentChange() { return percentChange; }
    public LocalDateTime getFetchedAt() { return fetchedAt; }

    public void setSymbol(String symbol) { this.symbol = symbol; }
    public void setPrice(double price) { this.price = price; }
    public void setOpen(double open) { this.open = open; }
    public void setHigh(double high) { this.high = high; }
    public void setLow(double low) { this.low = low; }
    public void setPreviousClose(double previousClose) { this.previousClose = previousClose; }
    public void setPercentChange(double percentChange) { this.percentChange = percentChange; }
    public void setFetchedAt(LocalDateTime fetchedAt) { this.fetchedAt = fetchedAt; }
}
