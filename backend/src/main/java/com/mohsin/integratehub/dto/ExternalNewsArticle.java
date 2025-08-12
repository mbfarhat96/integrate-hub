package com.mohsin.integratehub.dto;

public class ExternalNewsArticle {

    private String title;
    private String description;
    private String url;
    private ExternalNewsSource source;

    public ExternalNewsArticle() {}

    public ExternalNewsArticle(String title, String description, String url, String sourceName) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.source = new ExternalNewsSource(sourceName);
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUrl() { return url; }
    public ExternalNewsSource getSource() { return source; }
    public String getSourceName() { return source != null ? source.getName() : null; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setUrl(String url) { this.url = url; }
    public void setSource(ExternalNewsSource source) { this.source = source; }
}