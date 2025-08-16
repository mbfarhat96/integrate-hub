package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalGitHubRepo;
import com.mohsin.integratehub.dto.ExternalGitHubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Component
public class GitHubClient {

    private static final Logger log = LoggerFactory.getLogger(GitHubClient.class);

    private final WebClient webClient;
    private final String baseUrl;

    public GitHubClient(WebClient.Builder builder,
                        @Value("${integrations.github.base-url:https://api.github.com}") String baseUrl) {
        this.webClient = builder.build();
        this.baseUrl = baseUrl;
    }

    public ExternalGitHubUser getUser(String username) {
        try {
            log.info("Calling GitHub API for user {}", username);
            return webClient.get()
                    .uri(baseUrl + "/users/" + username)
                    .retrieve()
                    .bodyToMono(ExternalGitHubUser.class)
                    .block();
        } catch (WebClientResponseException ex) {
            handleGitHubError("user", username, ex);
            throw new GitHubClientException("Error fetching GitHub user", ex);
        }
    }

    public List<ExternalGitHubRepo> getUserRepos(String username) {
        try {
            log.info("Calling GitHub API for repos of {}", username);
            return webClient.get()
                    .uri(baseUrl + "/users/" + username + "/repos")
                    .retrieve()
                    .bodyToFlux(ExternalGitHubRepo.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException ex) {
            handleGitHubError("repos", username, ex);
            throw new GitHubClientException("Error fetching GitHub repos", ex);
        }
    }

    private void handleGitHubError(String type, String username, WebClientResponseException ex) {
        int status = ex.getRawStatusCode();
        String body = ex.getResponseBodyAsString();

        if (status == 403 || status == 429) {
            log.error("GitHub rate limit reached for {} of {}. status={} body={}",
                    type, username, status, body);
        } else {
            log.error("GitHub API error for {} of {}. status={} body={}",
                    type, username, status, body);
        }
    }


}
