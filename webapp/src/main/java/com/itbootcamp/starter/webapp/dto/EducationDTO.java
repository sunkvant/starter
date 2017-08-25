package com.itbootcamp.starter.webapp.dto;

import com.itbootcamp.starter.datamodel.impl.EducationTypeEntity;

public class EducationDTO {

    private Integer id;
    private String name;
    private String faculty;
    private String speciality;
    private Integer graduationYear;
    private EducationTypeDTO educationType;

    public EducationTypeDTO getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationTypeDTO educationType) {
        this.educationType = educationType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }
}
