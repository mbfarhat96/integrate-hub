package com.mohsin.integratehub.dto;

import java.util.List;

public class GitHubUserResponse {
    private String username;
    private String name;
    private String avatarUrl;
    private int publicRepos;
    private int followers;
    private int following;
    private List<GitHubRepoResponse> repositories;
    private String topLanguage;

    public GitHubUserResponse() {}

    public GitHubUserResponse(String username, String name, String avatarUrl,
                              int publicRepos, int followers, int following,
                              java.util.List<GitHubRepoResponse> repositories,
                              String topLanguage) {
        this.username = username;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
        this.repositories = repositories;
        this.topLanguage = topLanguage;
    }

    public String getTopLanguage() {
        return topLanguage;
    }

    public void setTopLanguage(String topLanguage) {
        this.topLanguage = topLanguage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public List<GitHubRepoResponse> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<GitHubRepoResponse> repositories) {
        this.repositories = repositories;
    }
}
