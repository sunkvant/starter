package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "vacancy")
public class VacancyEntity extends AbstractEntityID{
    private Integer personNumber;
    private PositionEntity position;
    private ProjectEntity project;
    private List<VacancyRequestEntity> vacancyRequests;
    private List<LanguageEntity> languages;
    private List<SkillEntity> skills;

    @Column(name = "person_number", nullable = false)
    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    @OneToMany(mappedBy = "vacancy")
    public List<VacancyRequestEntity> getVacancyRequests() {
        return vacancyRequests;
    }

    public void setVacancyRequests(List<VacancyRequestEntity> vacancyRequests) {
        this.vacancyRequests = vacancyRequests;
    }

    @ManyToMany(mappedBy = "vacancies")
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }

    @ManyToMany(mappedBy = "vacancies")
    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }
}
