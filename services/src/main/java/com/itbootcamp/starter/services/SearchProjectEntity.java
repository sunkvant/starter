package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 8/23/2017.
 */
public class SearchProjectEntity {
    private String name;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private List<ProjectStatusEntity> projectStatuses;
    private List<ProjectCategoryEntity> projectCategories;
    private List<LanguageEntity> languages;

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

    public List<ProjectStatusEntity> getProjectStatuses() {
        return projectStatuses;
    }

    public void setProjectStatuses(List<ProjectStatusEntity> projectStatuses) {
        this.projectStatuses = projectStatuses;
    }

    public List<ProjectCategoryEntity> getProjectCategories() {
        return projectCategories;
    }

    public void setProjectCategories(List<ProjectCategoryEntity> projectCategories) {
        this.projectCategories = projectCategories;
    }

    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }
}
