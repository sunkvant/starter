package com.itbootcamp.starter.webapp.dto;

import com.itbootcamp.starter.datamodel.impl.CourseEntity;
import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import com.itbootcamp.starter.datamodel.impl.WorkplaceEntity;

import java.util.List;

public class MentorDTO extends AbstractDTO {

    private String directionName;
    private Boolean isApprowed;
    private List<CourseEntity> courses;
    private List<WorkplaceEntity> workplaces;
    private List<EducationEntity> educations;
    private Boolean isMentorExp;
    private String experience;

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Boolean getApprowed() {
        return isApprowed;
    }

    public void setApprowed(Boolean approwed) {
        isApprowed = approwed;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }

    public List<WorkplaceEntity> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<WorkplaceEntity> workplaces) {
        this.workplaces = workplaces;
    }

    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }

    public Boolean getMentorExp() {
        return isMentorExp;
    }

    public void setMentorExp(Boolean mentorExp) {
        isMentorExp = mentorExp;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
