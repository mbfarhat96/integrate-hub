package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalNewsArticle;
import com.mohsin.integratehub.dto.ExternalNewsResponse;
import com.mohsin.integratehub.dto.NewsSearchResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsServiceTest {

    @Test
    void search_shouldNormalizeQueryAndMapArticles() {

        NewsClient client = mock(NewsClient.class);

        ExternalNewsArticle a1 = new ExternalNewsArticle(
                "Big Tech Stock Surges",
                "Shares surged due to quarterly earnings.",
                "http://example.com/1",
                "Bloomberg"
        );

        ExternalNewsArticle a2 = new ExternalNewsArticle(
                "Market Down Today",
                "Investors cautious after Fed remarks.",
                "http://example.com/2",
                "Reuters"
        );

        ExternalNewsResponse external = new ExternalNewsResponse(2, List.of(a1, a2));

        when(client.searchNews("tesla")).thenReturn(external);

        NewsService service = new NewsService(client);

        NewsSearchResponse result = service.search("  TESLA  ");

        assertEquals("tesla", result.getQuery());
        assertEquals(2, result.getTotal());
        assertEquals(2, result.getArticles().size());
        assertEquals("Big Tech Stock Surges", result.getArticles().get(0).getTitle());
    }
}
