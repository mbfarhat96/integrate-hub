package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.NewsSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsSnapshotScheduler {

    private static final Logger log = LoggerFactory.getLogger(NewsSnapshotScheduler.class);

    private final NewsService newsService;
    private final String snapshotQuery;

    public NewsSnapshotScheduler(NewsService newsService,
                                 @Value("${integrations.news.snapshot.query:technology}") String snapshotQuery) {
        this.newsService = newsService;
        this.snapshotQuery = snapshotQuery;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void snapshotDailyHeadlines() {
        try {
            NewsSearchResponse response = newsService.search(snapshotQuery);
            log.info("Daily news snapshot for '{}': {} results", snapshotQuery, response.getTotal());
        } catch (Exception ex) {
            log.error("Failed to take daily news snapshot for query={}", snapshotQuery, ex);
        }
    }
}
