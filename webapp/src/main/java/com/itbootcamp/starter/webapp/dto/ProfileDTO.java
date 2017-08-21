package com.itbootcamp.starter.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by admin on 8/14/2017.
 */
public class ProfileDTO {


    private Integer id;
    private String login;
    private String fullName;
    private Timestamp dateOfBirth;
    private String avatarPath;
    private String phone;
    private String skype;
    private String email;
    private String about;
    private RoleDTO role;
    private Boolean isBlocked;
    private DirectionDTO direction;
    private List<CourseDTO> courses;
    private List<WorkplaceDTO> workplaces;
    private List<EducationDTO> educations;
    private List<SkillDTO> skills;
    private Boolean isApproved;
    private Boolean isMentorExp;
    private String experience;

    public DirectionDTO getDirection() {
        return direction;
    }

    public void setDirection(DirectionDTO direction) {
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

    public ProfileDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
