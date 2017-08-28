package com.itbootcamp.starter.webapp.dto;

public class LocationDTO {

    private CountryDTO country;
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
