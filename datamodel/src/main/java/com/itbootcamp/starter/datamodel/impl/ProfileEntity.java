package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;

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

    @OneToMany(mappedBy = "profile",cascade =CascadeType.ALL)
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "profile",cascade =  CascadeType.ALL)
    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @MapsId
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

    @OneToMany(mappedBy = "profile",cascade =  CascadeType.ALL)
    public List<WorkplaceEntity> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<WorkplaceEntity> workplaces) {
        this.workplaces = workplaces;
    }

    @ManyToMany()
    @JoinTable(name = "profile_to_skill",
            joinColumns = @JoinColumn(name = "profile_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "skill_id", nullable = false))
    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

}
