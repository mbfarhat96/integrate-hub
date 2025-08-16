package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.CurrencyConversionResponse;
import com.mohsin.integratehub.service.CurrencyService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/api/currency")
    public ResponseEntity<CurrencyConversionResponse> convert(
            @RequestParam("from") @NotBlank String from,
            @RequestParam("to") @NotBlank String to,
            @RequestParam(value = "amount", defaultValue = "1.0") @Positive double amount
    ) {
        CurrencyConversionResponse response = currencyService.convert(from, to, amount);
        return ResponseEntity.ok(response);
    }
}
