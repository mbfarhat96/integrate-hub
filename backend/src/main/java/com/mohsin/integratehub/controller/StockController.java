package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.StockQuoteResponse;
import com.mohsin.integratehub.service.StockService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/api/stocks/quote")
    public ResponseEntity<StockQuoteResponse> getQuote(
            @RequestParam("symbol") @NotBlank String symbol
    ) {
        return ResponseEntity.ok(stockService.getQuote(symbol));
    }
}
