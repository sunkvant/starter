package com.itbootcamp.starter.webapp.dto;

import com.itbootcamp.starter.datamodel.impl.CourseEntity;
import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import com.itbootcamp.starter.datamodel.impl.SkillEntity;
import com.itbootcamp.starter.datamodel.impl.WorkplaceEntity;

import java.util.List;

/**
 * Created by admin on 8/15/2017.
 */
public class AbstractMemberDTO extends AbstractPersonDTO {

    private String directionName;
    private Boolean isApproved;
    private List<CourseEntity> courses;
    private List<WorkplaceEntity> workplaces;
    private List<EducationEntity> educations;
    private List<SkillEntity> skills;

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
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

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }
}
