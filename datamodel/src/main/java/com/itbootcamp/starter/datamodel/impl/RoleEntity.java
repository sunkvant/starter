package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "role")
public class RoleEntity  extends AbstractEntityID{
    @Expose private String name;
    private List<PersonEntity> person;

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role")
    public List<PersonEntity> getPerson() {
        return person;
    }

    public void setPerson(List<PersonEntity> person) {
        this.person = person;
    }
}
