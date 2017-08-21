package com.itbootcamp.starter.webapp.dto;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 8/17/2017.
 */
public class ProjectDTO {


    private Integer id;
    private String name;
    private String description;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private String contactInfo;
    private String projectStatus;
    private String projectCategory;
    private ProfileDTO customer;
    private List<LanguageDTO> languages;
    private List<VacancyDTO> vacancies;
    private List<MemberDTO> team;
    private List<ReviewDTO> reviews;

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfileDTO getCustomer() {
        return customer;
    }

    public void setCustomer(ProfileDTO customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public List<LanguageDTO> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDTO> languages) {
        this.languages = languages;
    }

    public List<VacancyDTO> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyDTO> vacancies) {
        this.vacancies = vacancies;
    }

    public List<MemberDTO> getTeam() {
        return team;
    }

    public void setTeam(List<MemberDTO> team) {
        this.team = team;
    }
}
