package com.mohsin.integratehub.dto;

import java.util.List;

public class NewsSearchResponse {

    private String query;
    private int total;
    private List<NewsArticleResponse> articles;

    public NewsSearchResponse() {}

    public NewsSearchResponse(String query, int total, List<NewsArticleResponse> articles) {
        this.query = query;
        this.total = total;
        this.articles = articles;
    }

    public String getQuery() { return query; }
    public int getTotal() { return total; }
    public List<NewsArticleResponse> getArticles() { return articles; }

    public void setQuery(String query) { this.query = query; }
    public void setTotal(int total) { this.total = total; }
    public void setArticles(List<NewsArticleResponse> articles) { this.articles = articles; }
}
