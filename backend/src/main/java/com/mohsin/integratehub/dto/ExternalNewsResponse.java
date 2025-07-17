package com.mohsin.integratehub.dto;

import java.util.List;

public class ExternalNewsResponse {

    private String status;
    private int totalResults;
    private List<ExternalNewsArticle> articles;

    public ExternalNewsResponse() {}

    public ExternalNewsResponse(String status, int totalResults, List<ExternalNewsArticle> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() { return status; }
    public int getTotalResults() { return totalResults; }
    public List<ExternalNewsArticle> getArticles() { return articles; }

    public void setStatus(String status) { this.status = status; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }
    public void setArticles(List<ExternalNewsArticle> articles) { this.articles = articles; }
}
