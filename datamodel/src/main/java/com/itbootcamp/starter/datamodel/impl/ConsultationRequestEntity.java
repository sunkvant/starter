package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "consultation_request")
public class ConsultationRequestEntity extends RequestEntity{
    private String title;
    private Boolean isSingle;
    private ProjectEntity project;

    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "is_single", nullable = false)
    public Boolean getSingle() {
        return isSingle;
    }

    public void setSingle(Boolean single) {
        isSingle = single;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
