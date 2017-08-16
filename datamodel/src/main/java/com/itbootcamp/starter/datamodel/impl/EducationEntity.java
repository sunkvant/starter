package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import javax.validation.groups.ConvertGroup;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "education")
public class EducationEntity extends AbstractEntityID{

    @Expose
    private String name;
    @Expose
    private String faculty;
    @Expose
    private String speciality;
    @Expose
    private Integer graduationYear;

    private ProfileEntity profile;

    @Expose @SerializedName(value = "educationType")
    private EducationTypeEntity educationTypeEntity;
    private Integer educationTypeId;

    private Integer profileId;

    @Column(name = "profile_id", nullable = false, insertable = false, updatable = false)
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }



    @Column(name = "education_type_id", nullable = false, insertable = false, updatable = false)
    public Integer getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(Integer educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "faculty", nullable = false, length = 255)
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Column(name = "speciality", nullable = false, length = 255)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Column(name = "graduation_year", nullable = false)
    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    //@JsonBackReference
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "education_type_id", referencedColumnName = "id", nullable = false)
    public EducationTypeEntity getEducationTypeEntity() {
        return educationTypeEntity;
    }

    public void setEducationTypeEntity(EducationTypeEntity educationEntity) {
        this.educationTypeEntity = educationEntity;
    }
}
