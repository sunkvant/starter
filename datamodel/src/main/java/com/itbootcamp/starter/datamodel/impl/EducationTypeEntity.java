package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "education_type")
public class EducationTypeEntity  extends AbstractEntityID{
    @Expose
    private String type;
    private List<EducationEntity> educations;

    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //@JsonManagedReference
    @OneToMany(mappedBy = "educationTypeEntity")
    @JsonIgnore
    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }
}
