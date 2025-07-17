package com.mohsin.integratehub.dto;

public class NewsArticleResponse {

    private String title;
    private String description;
    private String url;
    private String source;

    public NewsArticleResponse() {}

    public NewsArticleResponse(String title, String description, String url, String source) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.source = source;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUrl() { return url; }
    public String getSource() { return source; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setUrl(String url) { this.url = url; }
    public void setSource(String source) { this.source = source; }
}
