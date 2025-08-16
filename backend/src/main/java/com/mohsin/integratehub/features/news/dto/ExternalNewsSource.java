package com.mohsin.integratehub.dto;

public class ExternalNewsSource {

    private String name;

    public ExternalNewsSource() {}

    public ExternalNewsSource(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}