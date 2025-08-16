package com.mohsin.integratehub.service;

public class GitHubClientException extends RuntimeException {

    public GitHubClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
