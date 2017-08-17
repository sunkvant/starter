package com.itbootcamp.starter.webapp.dto;

import java.util.List;

/**
 * Created by admin on 8/17/2017.
 */
public class VacancyDTO {

    private Integer id;
    private Integer personNumber;
    private PositionDTO position;
    private RoleDTO role;
    private List<SkillDTO> skills;

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}