package com.itbootcamp.starter.webapp.dto;

/**
 * Created by admin on 9/3/2017.
 */
public class ConsultationRequestDTO extends AbstractRequestDTO{
    private String title;
    private Boolean isSingle;
    private ProjectDTO projectDTO;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSingle() {
        return isSingle;
    }

    public void setSingle(Boolean single) {
        isSingle = single;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }
}
