package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;

public class LocationDTO {

    @NotNull
    private CountryDTO country;
    @NotNull
    private CityDTO city;

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
