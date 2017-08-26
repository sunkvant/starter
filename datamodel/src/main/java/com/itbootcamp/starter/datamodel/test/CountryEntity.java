package com.itbootcamp.starter.datamodel.test;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/26/2017.
 */
@Entity
@Table(name = "country")
public class CountryEntity {
    private Integer id;
    private String name;
    private List<CityEntity> cities;
    private List<LocationEntity> locations;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country")
    public List<CityEntity> getCities() {
        return cities;
    }

    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    @OneToMany(mappedBy = "country")
    public List<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }
}
