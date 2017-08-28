package com.itbootcamp.starter.datamodel;

import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "team")
public class TeamEntity extends AbstractEntityID {

    private Boolean isMember;
    private ProjectEntity project;
    private PersonEntity person;
    private PositionEntity position;

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

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }
}
