package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "direction")
public class DirectionEntity extends AbstractEntityID{
    private String name;
    @JsonIgnore
    private List<ProfileEntity> profiles;

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "direction")
    public List<ProfileEntity> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileEntity> profiles) {
        this.profiles = profiles;
    }
}
