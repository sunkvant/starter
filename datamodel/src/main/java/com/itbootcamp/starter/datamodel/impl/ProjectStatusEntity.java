package com.itbootcamp.starter.datamodel.impl;
import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "project_status")
public class ProjectStatusEntity  extends AbstractEntityID{
    private String status;
    private List<ProjectEntity> projects;

    @Column(name = "status", nullable = false, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "projectStatus")
    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }
}
