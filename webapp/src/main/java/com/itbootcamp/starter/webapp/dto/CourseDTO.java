package com.itbootcamp.starter.webapp.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CourseDTO {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String organization;
    @NotNull
    private String speciality;
    @NotNull
    private Integer graduationYear;


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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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


    @Override
    public int hashCode() {

        int result=17;

        result=37 * result + (organization == null ? 0 : organization.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        CourseDTO courseDTO=(CourseDTO) obj;

        if (this.organization.equals(courseDTO.getName())) {

            return true;

        }

        return false;
    }
}
