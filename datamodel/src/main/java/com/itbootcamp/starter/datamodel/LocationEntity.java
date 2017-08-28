package com.itbootcamp.starter.datamodel;

import javax.persistence.*;
/**
 * Created by admin on 8/26/2017.
 */
@Entity
@Table(name = "location")
public class LocationEntity extends AbstractEntityID{
    private ContactEntity contact;
    private CityEntity city;
    private CountryEntity country;

    @OneToOne(mappedBy = "location")
    public ContactEntity getContact() {
        return contact;
    }
    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public CityEntity getCity() {
        return city;
    }
    public void setCity(CityEntity city) {
        this.city = city;
    }
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    public CountryEntity getCountry() {
        return country;
    }
    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}