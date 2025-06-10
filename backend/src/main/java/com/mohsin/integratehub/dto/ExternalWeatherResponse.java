package com.mohsin.integratehub.dto;

public class ExternalWeatherResponse {

    private String name;        // city name
    private String country;     // country code
    private double temp;        // temperature in Celsius
    private double feelsLike;   // feels like in Celsius
    private int humidity;       // humidity %
    private String description; // short weather description

    public ExternalWeatherResponse() {
    }

    public ExternalWeatherResponse(String name,
                                   String country,
                                   double temp,
                                   double feelsLike,
                                   int humidity,
                                   String description) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
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
}
