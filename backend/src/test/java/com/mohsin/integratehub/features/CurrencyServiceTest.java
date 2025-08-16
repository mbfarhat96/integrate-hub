package com.mohsin.integratehub.service;

import com.mohsin.integratehub.features.currency.CurrencyClient;
import com.mohsin.integratehub.features.currency.CurrencyService;
import com.mohsin.integratehub.features.currency.dto.CurrencyConversionResponse;
import com.mohsin.integratehub.features.currency.dto.ExternalCurrencyResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @Test
    void convert_shouldComputeCrossRateWhenApiBaseIsDifferent() {
        CurrencyClient client = mock(CurrencyClient.class);

        ExternalCurrencyResponse external = new ExternalCurrencyResponse(
                "EUR",
                LocalDateTime.now().toLocalDate().toString(),
                Map.of(
                        "USD", 1.1,
                        "PKR", 300.0
                )
        );

        when(client.getRates("USD", "PKR")).thenReturn(external);

        CurrencyService service = new CurrencyService(client);

        CurrencyConversionResponse result = service.convert("usd", "pkr", 10.0);

        assertEquals("USD", result.getFromCurrency());
        assertEquals("PKR", result.getToCurrency());
        assertEquals(10.0, result.getAmount());
        assertEquals(272.727273, result.getRate(), 0.000001);
        assertEquals(2727.27, result.getConvertedAmount(), 0.000001);
        assertNotNull(result.getFetchedAt());
    }
}
