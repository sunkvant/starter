package com.itbootcamp.starter.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "skill")
public class SkillEntity  extends AbstractEntityID{
    private String name;
    private List<ProfileEntity> profiles;
    private List<VacancyEntity> vacancies;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "skills")
/*    @ManyToMany
    @JoinTable(name = "profile_to_skill",
            joinColumns = @JoinColumn(name = "skill_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "profile_id", nullable = false))*/
    public List<ProfileEntity> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileEntity> profiles) {
        this.profiles = profiles;
    }

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "skill_to_vacancy", joinColumns = @JoinColumn(name = "skill_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id", nullable = false))
    public List<VacancyEntity> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyEntity> vacancies) {
        this.vacancies = vacancies;
    }
}
