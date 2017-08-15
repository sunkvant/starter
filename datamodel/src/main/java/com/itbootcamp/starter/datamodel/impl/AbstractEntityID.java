package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;

/**
 * Created by admin on 8/9/2017.
 */
@MappedSuperclass
public abstract class AbstractEntityID {

    protected Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
