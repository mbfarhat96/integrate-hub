package com.mohsin.integratehub.controller;

import com.mohsin.integratehub.dto.GitHubUserResponse;
import com.mohsin.integratehub.service.GitHubService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/api/github/user/{username}")
    public ResponseEntity<GitHubUserResponse> getGitHubUser(
            @PathVariable("username") @NotBlank String username
    ) {
        GitHubUserResponse response = gitHubService.getUserWithRepos(username);
        return ResponseEntity.ok(response);
    }
}
