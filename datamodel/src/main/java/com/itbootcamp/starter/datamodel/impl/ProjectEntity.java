package com.itbootcamp.starter.datamodel.impl;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "project")
public class ProjectEntity  extends AbstractEntityID{
    private String name;
    private String description;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private Integer projectStatusId;
    private Integer projectCategoryId;
    private String contactInfo;
    private List<ConsultationRequestEntity> consultations;
    private PersonEntity customer;
    private ProjectStatusEntity projectStatus;
    private ProjectCategoryEntity projectCategory;
    private List<ReviewEntity> reviews;
    private List<TeamEntity> statuses;
    private List<VacancyEntity> vacancies;
    private List<LanguageEntity> languages;

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "date_start", nullable = false)
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Column(name = "date_end", nullable = false)
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Column(name = "project_status_id", nullable = false, insertable = false, updatable = false)
    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Column(name = "project_category_id", nullable = false, insertable = false, updatable = false)
    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    @Column(name = "contact_info", nullable = false, length = 255)
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @OneToMany(mappedBy = "project")
    public List<ConsultationRequestEntity> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<ConsultationRequestEntity> consultations) {
        this.consultations = consultations;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public PersonEntity getCustomer() {
        return customer;
    }

    public void setCustomer(PersonEntity customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "project_status_id", referencedColumnName = "id", nullable = false)
    public ProjectStatusEntity getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatusEntity projectStatus) {
        this.projectStatus = projectStatus;
    }

    @ManyToOne
    @JoinColumn(name = "project_category_id", referencedColumnName = "id", nullable = false)
    public ProjectCategoryEntity getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategoryEntity projectCategory) {
        this.projectCategory = projectCategory;
    }

    @OneToMany(mappedBy = "project")
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(mappedBy = "project")
    public List<TeamEntity> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<TeamEntity> statuses) {
        this.statuses = statuses;
    }

    @OneToMany(mappedBy = "project")
    public List<VacancyEntity> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyEntity> vacancies) {
        this.vacancies = vacancies;
    }

    @ManyToMany(mappedBy = "projects")
    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }
}
