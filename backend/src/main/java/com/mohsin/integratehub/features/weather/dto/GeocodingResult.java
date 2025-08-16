package com.mohsin.integratehub.dto;

public class GeocodingResult {

    private final double latitude;
    private final double longitude;
    private final String country;

    public GeocodingResult(double latitude, double longitude, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }
}