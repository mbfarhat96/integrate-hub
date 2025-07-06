package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.CurrencyConversionResponse;
import com.mohsin.integratehub.dto.ExternalCurrencyResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @Test
    void convert_shouldUseRateAndReturnRoundedValues() {
        CurrencyClient client = mock(CurrencyClient.class);

        ExternalCurrencyResponse external = new ExternalCurrencyResponse(
                "USD",
                LocalDateTime.now().toLocalDate().toString(),
                Map.of("PKR", 278.123456)
        );

        when(client.getRates("USD")).thenReturn(external);

        CurrencyService service = new CurrencyService(client);

        CurrencyConversionResponse result = service.convert("usd", "pkr", 10.0);

        assertEquals("USD", result.getFromCurrency());
        assertEquals("PKR", result.getToCurrency());
        assertEquals(10.0, result.getAmount());
        assertEquals(278.123456, result.getRate(), 0.000001);
        assertEquals(2781.23, result.getConvertedAmount(), 0.000001);
        assertNotNull(result.getFetchedAt());
    }
}
