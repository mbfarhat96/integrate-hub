package com.mohsin.integratehub.service;

public class StockClientException extends RuntimeException {

    public StockClientException(String message) {
        super(message);
    }

    public StockClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
