package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.NewsSearchResponse;
import com.mohsin.integratehub.service.NewsService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/news")
    public ResponseEntity<NewsSearchResponse> search(
            @RequestParam("query") @NotBlank String query
    ) {
        return ResponseEntity.ok(newsService.search(query));
    }
}
