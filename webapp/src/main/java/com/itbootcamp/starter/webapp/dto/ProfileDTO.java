package com.itbootcamp.starter.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 8/14/2017.
 */
public class ProfileDTO {

    private Integer id;

    @NotNull
    @NotEmpty
    private String login;
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull
    private ContactDTO contact;
    @NotNull
    private String role;
    private String direction;
    @NotNull
    private List<CourseDTO> courses;
    @NotNull
    private List<WorkplaceDTO> workplaces;
    @NotNull
    private List<EducationDTO> educations;
    @NotNull
    private List<String> skills;
    private Boolean isMentorExp;
    private String experience;
    private Boolean isApproved;
    private Boolean isBlocked;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
