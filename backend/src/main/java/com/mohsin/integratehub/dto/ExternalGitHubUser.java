package com.mohsin.integratehub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalGitHubUser {

    private String login;
    private String name;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("public_repos")
    private int publicRepos;

    private int followers;
    private int following;

    public ExternalGitHubUser() {}

    public ExternalGitHubUser(String login, String name, String avatarUrl,
                              int publicRepos, int followers, int following) {
        this.login = login;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        return publicPublicRepos();
    }

    public int publicPublicRepos() {
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
}
