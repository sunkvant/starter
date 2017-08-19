package com.itbootcamp.starter.webapp.dto;

import java.sql.Timestamp;

public class WorkplaceDTO {

    private Integer id;
    private String company;
    private String sphereOfActivity;
    private String position;
    private String duties;
    private Timestamp startWork;
    private Timestamp endWork;
    private Boolean isWorking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSphereOfActivity() {
        return sphereOfActivity;
    }

    public void setSphereOfActivity(String sphereOfActivity) {
        this.sphereOfActivity = sphereOfActivity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public Timestamp getStartWork() {
        return startWork;
    }

    public void setStartWork(Timestamp startWork) {
        this.startWork = startWork;
    }

    public Timestamp getEndWork() {
        return endWork;
    }

    public void setEndWork(Timestamp endWork) {
        this.endWork = endWork;
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }
}
