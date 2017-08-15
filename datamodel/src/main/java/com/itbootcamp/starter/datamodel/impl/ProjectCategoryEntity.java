package com.itbootcamp.starter.datamodel.impl;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "project_category")
public class ProjectCategoryEntity extends AbstractEntityID{
    @Expose private String category;
    private List<ProjectEntity> projects;

    @Column(name = "category", nullable = false, length = 255)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "projectCategory")
    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }
}
