package com.itbootcamp.starter.datamodel.impl;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "position")
public class PositionEntity  extends AbstractEntityID{
    @Expose private String name;

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
