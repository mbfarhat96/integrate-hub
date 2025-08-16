package com.mohsin.integratehub.service;

import com.mohsin.integratehub.features.stocks.StockClient;
import com.mohsin.integratehub.features.stocks.StockService;
import com.mohsin.integratehub.features.stocks.dto.ExternalStockResponse;
import com.mohsin.integratehub.features.stocks.dto.StockQuoteResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @Test
    void getQuote_shouldCalculatePercentChange() {

        StockClient client = mock(StockClient.class);

        ExternalStockResponse external = new ExternalStockResponse(
                "AAPL",
                200.00,   // price now
                195.00,   // open
                205.00,   // high
                194.50,   // low
                190.00    // previous close
        );

        when(client.getQuote("AAPL")).thenReturn(external);

        StockService service = new StockService(client);

        StockQuoteResponse result = service.getQuote("aapl");

        assertEquals("AAPL", result.getSymbol());
        assertEquals(200.00, result.getPrice());
        assertEquals(195.00, result.getOpen());
        assertEquals(205.00, result.getHigh());
        assertEquals(194.50, result.getLow());
        assertEquals(190.00, result.getPreviousClose());

        // percent change = ((200 - 190) / 190) * 100 ≈ 5.26%
        assertEquals(5.26, result.getPercentChange(), 0.01);

        assertNotNull(result.getFetchedAt());
    }
}
