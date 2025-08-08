package com.mohsin.integratehub.dto;

public class StockSearchResult {

    private String symbol;
    private String shortName;
    private String longName;
    private String exchange;
    private String type;

    public StockSearchResult() {
    }

    public StockSearchResult(String symbol, String shortName, String longName, String exchange, String type) {
        this.symbol = symbol;
        this.shortName = shortName;
        this.longName = longName;
        this.exchange = exchange;
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getExchange() {
        return exchange;
    }

    public String getType() {
        return type;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setType(String type) {
        this.type = type;
    }
}