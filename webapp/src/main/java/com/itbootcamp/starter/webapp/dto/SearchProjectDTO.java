package com.itbootcamp.starter.webapp.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 8/23/2017.
 */
public class SearchProjectDTO {

    private String name;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private List<String> projectCategories;
    private List<String> projectStatuses;
    private List<String> languages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getProjectCategories() {
        return projectCategories;
    }

    public void setProjectCategories(List<String> projectCategories) {
        this.projectCategories = projectCategories;
    }

    public List<String> getProjectStatuses() {
        return projectStatuses;
    }

    public void setProjectStatuses(List<String> projectStatuses) {
        this.projectStatuses = projectStatuses;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
