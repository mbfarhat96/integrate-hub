package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.StockQuoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockSnapshotScheduler {

    private static final Logger log = LoggerFactory.getLogger(StockSnapshotScheduler.class);

    private final StockService stockService;
    private final String snapshotSymbol;

    public StockSnapshotScheduler(StockService stockService,
                                  @Value("${integrations.stocks.snapshot.symbol:AAPL}") String snapshotSymbol) {
        this.stockService = stockService;
        this.snapshotSymbol = snapshotSymbol;
    }

    @Scheduled(cron = "0 30 9 * * *") // 9:30am
    public void snapshotDailyQuote() {
        try {
            StockQuoteResponse data = stockService.getQuote(snapshotSymbol);
            log.info("Daily stock snapshot: {} price={} change={}%",
                    data.getSymbol(),
                    data.getPrice(),
                    data.getPercentChange());
        } catch (Exception ex) {
            log.error("Failed daily snapshot for symbol={}", snapshotSymbol, ex);
        }
    }
}
