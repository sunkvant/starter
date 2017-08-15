package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "workplace")
public class WorkplaceEntity  extends AbstractEntityID{
    private String company;
    private String sphereOfActivity;
    private String position;
    private String duties;
    private Timestamp startWork;
    private Timestamp endWork;
    private Boolean isWorking;
    private ProfileEntity profile;

    @Column(name = "company", nullable = false, length = 255)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "sphere_of_activity", nullable = false, length = 255)
    public String getSphereOfActivity() {
        return sphereOfActivity;
    }

    public void setSphereOfActivity(String sphereOfActivity) {
        this.sphereOfActivity = sphereOfActivity;
    }

    @Column(name = "position", nullable = false, length = 255)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "duties", nullable = false, length = 255)
    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    @Column(name = "start_work", nullable = false)
    public Timestamp getStartWork() {
        return startWork;
    }

    public void setStartWork(Timestamp startWork) {
        this.startWork = startWork;
    }

    @Column(name = "end_work", nullable = false)
    public Timestamp getEndWork() {
        return endWork;
    }

    public void setEndWork(Timestamp endWork) {
        this.endWork = endWork;
    }

    @Column(name = "is_working", nullable = false)
    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
}
