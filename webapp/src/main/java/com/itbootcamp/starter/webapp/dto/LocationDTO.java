package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;

public class LocationDTO {

    @NotNull
    private String country;
    @NotNull
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
