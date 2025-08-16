package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.CurrencyConversionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencySnapshotScheduler {

    private static final Logger log = LoggerFactory.getLogger(CurrencySnapshotScheduler.class);

    private final CurrencyService currencyService;
    private final String baseCurrency;
    private final String targetCurrency;

    public CurrencySnapshotScheduler(CurrencyService currencyService,
                                     @Value("${integrations.currency.snapshot.base:USD}") String baseCurrency,
                                     @Value("${integrations.currency.snapshot.target:PKR}") String targetCurrency) {
        this.currencyService = currencyService;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    /**
     * Take a daily snapshot of a base->target rate.
     * Runs every day at 09:00 server time.
     */
    @Scheduled(cron = "0 0 9 * * *")
    public void snapshotDailyRate() {
        try {
            CurrencyConversionResponse response = currencyService.convert(baseCurrency, targetCurrency, 1.0);
            log.info("Daily currency snapshot: {} -> {} rate={} at {}",
                    response.getFromCurrency(),
                    response.getToCurrency(),
                    response.getRate(),
                    response.getFetchedAt());
        } catch (Exception ex) {
            log.error("Failed to take daily currency snapshot for {}->{}", baseCurrency, targetCurrency, ex);
        }
    }
}
