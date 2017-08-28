package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;

public class EducationDTO {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String faculty;
    @NotNull
    private String speciality;
    @NotNull
    private Integer graduationYear;
    @NotNull
    private String educationType;

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
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
