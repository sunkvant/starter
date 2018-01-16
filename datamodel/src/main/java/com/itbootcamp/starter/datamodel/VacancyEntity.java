package com.itbootcamp.starter.datamodel;


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
    private Boolean isActive;
    private ProjectEntity project;
    private List<VacancyRequestEntity> vacancyRequests;
    private List<LanguageEntity> languages;
    private List<SkillEntity> skills;
    private RoleEntity role;

    @Column(name = "person_number", nullable = false)
    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
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

    @ManyToMany
    @JoinTable(name = "vacancy_to_language",
            joinColumns = @JoinColumn(name = "vacancy_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "language_id", nullable = false))
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }

    @ManyToMany
    @JoinTable(name = "skill_to_vacancy", joinColumns = @JoinColumn(name = "vacancy_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "skill_id", nullable = false))
    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Column(name = "is_active", nullable = false)
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
