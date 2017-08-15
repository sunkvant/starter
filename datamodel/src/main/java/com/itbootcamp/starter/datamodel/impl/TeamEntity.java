package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "team", schema = "public", catalog = "starter")
public class TeamEntity extends AbstractEntityID {
    private Boolean isMember;
    private ProjectEntity project;
    private PersonEntity person;

    @Basic
    @Column(name = "is_member", nullable = false)
    public Boolean getMember() {
        return isMember;
    }

    public void setMember(Boolean member) {
        isMember = member;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity projectByProjectId) {
        this.project = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity personByPersonId) {
        this.person = personByPersonId;
    }
}
