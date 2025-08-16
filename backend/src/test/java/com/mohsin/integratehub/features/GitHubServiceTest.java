package com.mohsin.integratehub.service;

import com.mohsin.integratehub.features.github.GitHubClient;
import com.mohsin.integratehub.features.github.GitHubService;
import com.mohsin.integratehub.features.github.dto.ExternalGitHubRepo;
import com.mohsin.integratehub.features.github.dto.ExternalGitHubUser;
import com.mohsin.integratehub.features.github.dto.GitHubUserResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GitHubServiceTest {

    @Test
    void getUserWithRepos_shouldMapExternalDataAndComputeTopLanguage() {
        GitHubClient client = mock(GitHubClient.class);

        ExternalGitHubUser externalUser = new ExternalGitHubUser(
                "octocat",
                "The Octocat",
                "https://avatars.githubusercontent.com/u/1",
                3,
                10,
                5
        );

        ExternalGitHubRepo repo1 = new ExternalGitHubRepo("repo1", 5, 1, "Java");
        ExternalGitHubRepo repo2 = new ExternalGitHubRepo("repo2", 10, 2, "Java");
        ExternalGitHubRepo repo3 = new ExternalGitHubRepo("repo3", 1, 0, "JavaScript");

        when(client.getUser("octocat")).thenReturn(externalUser);
        when(client.getUserRepos("octocat")).thenReturn(List.of(repo1, repo2, repo3));

        GitHubService service = new GitHubService(client);

        GitHubUserResponse result = service.getUserWithRepos("octocat");

        assertEquals("octocat", result.getUsername());
        assertEquals("The Octocat", result.getName());
        assertEquals("https://avatars.githubusercontent.com/u/1", result.getAvatarUrl());
        assertEquals(3, result.getPublicRepos());
        assertEquals(10, result.getFollowers());
        assertEquals(5, result.getFollowing());
        assertEquals(3, result.getRepositories().size());
        assertEquals("Java", result.getTopLanguage()); // most frequent
    }
}
