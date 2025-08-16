package com.mohsin.integratehub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AlphaSearchResponse {

    @JsonProperty("bestMatches")
    private List<AlphaSearchMatch> bestMatches;

    public List<AlphaSearchMatch> getBestMatches() {
        return bestMatches;
    }

    public void setBestMatches(List<AlphaSearchMatch> bestMatches) {
        this.bestMatches = bestMatches;
    }
}