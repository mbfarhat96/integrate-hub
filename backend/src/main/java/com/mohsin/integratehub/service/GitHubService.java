package com.mohsin.integratehub.service;

import com.mohsin.integratehub.dto.ExternalGitHubRepo;
import com.mohsin.integratehub.dto.ExternalGitHubUser;
import com.mohsin.integratehub.dto.GitHubRepoResponse;
import com.mohsin.integratehub.dto.GitHubUserResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        return new GitHubUserResponse(
                externalUser.getLogin(),
                externalUser.getName(),
                externalUser.getAvatarUrl(),
                externalUser.getPublicRepos(),
                externalUser.getFollowers(),
                externalUser.getFollowing(),
                repos
        );
    }
}
