package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;


import javax.persistence.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
>>>>>>> datamodel/src/main/java/com/itbootcamp/starter/datamodel/impl/RoleEntity.java
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "role")
public class RoleEntity  extends AbstractEntityID
    implements Serializable

{
    @Expose private String name;

    @JsonIgnore
    private List<PersonEntity> person;
    @JsonIgnore
    private List<VacancyEntity> vacancies;

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

    @OneToMany(mappedBy = "role")
    public List<VacancyEntity> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyEntity> vacancies) {
        this.vacancies = vacancies;
    }
}
