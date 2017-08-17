package com.itbootcamp.starter.webapp.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 8/17/2017.
 */
public class ProjectDTO {


    private Integer customerId;
    private String name;
    private String description;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private String contactInfo;
    private String projectStatus;
    private String projectCategory;
    private List<String> languages;
    private List<VacancyDTO> vacancies;
    private List<MemberDTO> teamActive;
    private List<MemberDTO> teamNoActive;

    public ProjectDTO convert() {

        this



    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<VacancyDTO> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacancyDTO> vacancies) {
        this.vacancies = vacancies;
    }

    public List<MemberDTO> getTeamActive() {
        return teamActive;
    }

    public void setTeamActive(List<MemberDTO> teamActive) {
        this.teamActive = teamActive;
    }

    public List<MemberDTO> getTeamNoActive() {
        return teamNoActive;
    }

    public void setTeamNoActive(List<MemberDTO> teamNoActive) {
        this.teamNoActive = teamNoActive;
    }json.addProperty("customerId", projectEntity.getCustomer().getId());
        json.addProperty("name", projectEntity.getName());
        json.addProperty("description", projectEntity.getDescription());
        json.addProperty("dateStart", projectEntity.getDateStart().toString());
        json.addProperty("dateEnd", projectEntity.getDateEnd().toString());
        json.addProperty("contactInfo", projectEntity.getContactInfo());
        json.addProperty("projectStatus", projectEntity.getProjectStatus().getStatus());
        json.addProperty("projectCategory", projectEntity.getProjectCategory().getCategory());


}
