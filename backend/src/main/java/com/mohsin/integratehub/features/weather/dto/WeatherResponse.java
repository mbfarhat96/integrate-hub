package com.mohsin.integratehub.dto;

import java.time.LocalDateTime;

public class WeatherResponse {

    private String city;
    private String country;
    private double temperatureCelsius;
    private double feelsLikeCelsius;
    private int humidity;
    private String description;
    private LocalDateTime fetchedAt;

    public WeatherResponse() {
    }

    public WeatherResponse(String city,
                           String country,
                           double temperatureCelsius,
                           double feelsLikeCelsius,
                           int humidity,
                           String description,
                           LocalDateTime fetchedAt) {
        this.city = city;
        this.country = country;
        this.temperatureCelsius = temperatureCelsius;
        this.feelsLikeCelsius = feelsLikeCelsius;
        this.humidity = humidity;
        this.description = description;
        this.fetchedAt = fetchedAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public double getFeelsLikeCelsius() {
        return feelsLikeCelsius;
    }

    public void setFeelsLikeCelsius(double feelsLikeCelsius) {
        this.feelsLikeCelsius = feelsLikeCelsius;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }
}
