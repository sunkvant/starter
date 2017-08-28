package com.itbootcamp.starter.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "education_type")
public class EducationTypeEntity  extends AbstractEntityID{
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
