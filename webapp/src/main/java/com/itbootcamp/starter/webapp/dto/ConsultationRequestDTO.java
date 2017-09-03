package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by admin on 9/4/2017.
 */
public class ConsultationRequestDTO {

    @NotNull
    private Integer projectId;
    private String title;
    @NotNull
    private Boolean isSingle;
    @NotNull
    private Integer receiverPersonId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

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

    public Integer getReceiverPersonId() {
        return receiverPersonId;
    }

    public void setReceiverPersonId(Integer receiverPersonId) {
        this.receiverPersonId = receiverPersonId;
    }
}
