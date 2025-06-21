package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalGitHubRepo;
import com.mohsin.integratehub.dto.ExternalGitHubUser;
import com.mohsin.integratehub.dto.GitHubRepoResponse;
import com.mohsin.integratehub.dto.GitHubUserResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class GitHubService {

    private final GitHubClient gitHubClient;

    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Cacheable(cacheNames = "githubUserStats")
    public GitHubUserResponse getUserWithRepos(String username) {
        ExternalGitHubUser externalUser = gitHubClient.getUser(username);
        List<ExternalGitHubRepo> externalRepos = gitHubClient.getUserRepos(username);

        List<GitHubRepoResponse> repos = externalRepos.stream()
                .map(r -> new GitHubRepoResponse(
                        r.getName(),
                        r.getStargazersCount(),
                        r.getForksCount(),
                        r.getLanguage()
                ))
                .collect(Collectors.toList());

        String topLanguage = calculateTopLanguage(repos);

        return new GitHubUserResponse(
                externalUser.getLogin(),
                externalUser.getName(),
                externalUser.getAvatarUrl(),
                externalUser.getPublicRepos(),
                externalUser.getFollowers(),
                externalUser.getFollowing(),
                repos,
                topLanguage
        );
    }

    private String calculateTopLanguage(List<GitHubRepoResponse> repos) {
        Map<String, Long> counts = repos.stream()
                .map(GitHubRepoResponse::getLanguage)
                .filter(lang -> lang != null && !lang.isBlank())
                .collect(groupingBy(Function.identity(), counting()));

        return counts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
