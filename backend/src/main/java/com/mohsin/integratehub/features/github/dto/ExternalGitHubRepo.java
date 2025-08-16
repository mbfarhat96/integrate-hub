package com.mohsin.integratehub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalGitHubRepo {

    private String name;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

    @JsonProperty("forks_count")
    private int forksCount;

    private String language;

    public ExternalGitHubRepo() {}

    public ExternalGitHubRepo(String name, int stargazersCount, int forksCount, String language) {
        this.name = name;
        this.stargazersCount = stargazersCount;
        this.forksCount = forksCount;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
