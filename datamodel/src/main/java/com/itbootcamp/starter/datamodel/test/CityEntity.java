package com.itbootcamp.starter.datamodel.test;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/26/2017.
 */
@Entity
@Table(name = "city")
public class CityEntity {
    private Integer id;
    private String name;
    private CountryEntity country;
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

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "city")
    public List<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }
}
