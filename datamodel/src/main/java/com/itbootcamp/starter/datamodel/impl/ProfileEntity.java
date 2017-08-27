package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "profile")
public class ProfileEntity  extends AbstractEntityID implements Serializable {
    private Integer directionId;
    private Boolean isApproved;
    private List<CourseEntity> courses;
    private List<EducationEntity> educations;
    private PersonEntity person;
    private DirectionEntity direction;
    private List<WorkplaceEntity> workplaces;
    private List<SkillEntity> skills;
    private MentorInfoEntity mentorInfo;

    @Column(name = "direction_id", nullable = false, insertable = false, updatable = false)
    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    @Column(name = "is_approved", nullable = false)
    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    @OneToMany(mappedBy = "profile")
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "profile")
    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @ManyToOne
    @JoinColumn(name = "direction_id", referencedColumnName = "id", nullable = false)
    public DirectionEntity getDirection() {
        return direction;
    }

    public void setDirection(DirectionEntity direction) {
        this.direction = direction;
    }

    @OneToMany(mappedBy = "profile")
    public List<WorkplaceEntity> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<WorkplaceEntity> workplaces) {
        this.workplaces = workplaces;
    }

    @ManyToMany(mappedBy = "profiles",fetch = FetchType.LAZY)
    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    @OneToOne(mappedBy = "profile",fetch = FetchType.LAZY)
    public MentorInfoEntity getMentorInfo() {
        return mentorInfo;
    }

    public void setMentorInfo(MentorInfoEntity mentorInfo) {
        this.mentorInfo = mentorInfo;
    }

}
