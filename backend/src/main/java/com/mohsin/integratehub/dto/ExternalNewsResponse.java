package com.mohsin.integratehub.dto;

import java.util.List;

public class ExternalNewsResponse {

    private int totalArticles;
    private List<ExternalNewsArticle> articles;

    public ExternalNewsResponse() {}

    public ExternalNewsResponse(int totalArticles, List<ExternalNewsArticle> articles) {
        this.totalArticles = totalArticles;
        this.articles = articles;
    }

    public int getTotalArticles() { return totalArticles; }
    public List<ExternalNewsArticle> getArticles() { return articles; }

    public void setTotalArticles(int totalArticles) { this.totalArticles = totalArticles; }
    public void setArticles(List<ExternalNewsArticle> articles) { this.articles = articles; }
}