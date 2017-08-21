package com.itbootcamp.starter.datamodel.impl;


import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "position")
public class PositionEntity  extends AbstractEntityID{
    private String name;
    private List<TeamEntity> teams;
    private List<VacancyEntity> vacancies;

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "position")
    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }

    @OneToMany(mappedBy = "position")
    public List<VacancyEntity> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyEntity> vacancies) {
        this.vacancies = vacancies;
    }

}
