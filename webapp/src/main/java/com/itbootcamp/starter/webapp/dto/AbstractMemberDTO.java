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

    private DirectionDTO direction;
    private List<CourseDTO> courses;
    private List<WorkplaceDTO> workplaces;
    private List<EducationDTO> educations;
    private List<SkillDTO> skills;
    private Boolean isApproved;

    public DirectionDTO getDirection() {
        return direction;
    }

    public void setDirection(DirectionDTO direction) {
        this.direction = direction;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public List<WorkplaceDTO> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(List<WorkplaceDTO> workplaces) {
        this.workplaces = workplaces;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }
}
