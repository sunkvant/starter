package com.itbootcamp.starter.datamodel;


import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "language")
public class LanguageEntity extends AbstractEntityID{
    private String name;
    private List<ProjectEntity> projects;
    private List<VacancyEntity> vacancies;

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "languages")
    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    @ManyToMany(mappedBy = "languages")
    public List<VacancyEntity> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyEntity> vacancies) {
        this.vacancies = vacancies;
    }
}
