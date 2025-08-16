package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalNewsArticle;
import com.mohsin.integratehub.dto.ExternalNewsResponse;
import com.mohsin.integratehub.dto.NewsArticleResponse;
import com.mohsin.integratehub.dto.NewsSearchResponse;
import com.mohsin.integratehub.service.NewsClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsClient newsClient;

    public NewsService(NewsClient newsClient) {
        this.newsClient = newsClient;
    }

    @Cacheable(cacheNames = "newsSearch")
    public NewsSearchResponse search(String query) {

        String normalized = normalize(query);

        ExternalNewsResponse external = newsClient.searchNews(normalized);

        List<ExternalNewsArticle> articles = external != null && external.getArticles() != null
                ? external.getArticles()
                : List.of();

        List<NewsArticleResponse> mapped = articles.stream()
                .filter(a -> a.getTitle() != null && !a.getTitle().isBlank())
                .map(this::mapArticle)
                .distinct()
                .limit(20) // cap results for clean API output
                .collect(Collectors.toList());

        return new NewsSearchResponse(
                normalized,
                mapped.size(),
                mapped
        );
    }

    private NewsArticleResponse mapArticle(ExternalNewsArticle a) {
        String desc = a.getDescription();
        if (desc != null && desc.length() > 160) {
            desc = desc.substring(0, 157) + "...";
        }

        return new NewsArticleResponse(
                a.getTitle(),
                desc,
                a.getUrl(),
                a.getSourceName()
        );
    }

    private String normalize(String q) {
        return q.trim().toLowerCase(Locale.ROOT);
    }
}