package com.itbootcamp.starter.webapp.dto;

/**
 * Created by admin on 8/17/2017.
 */
public class MemberDTO {

    private ProfileDTO member;
    private PositionDTO position;
    private RoleDTO role;
    private Boolean isActive;

    public void setMember(ProfileDTO member) {
        this.member = member;
    }

    public ProfileDTO getMember() {
        return member;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean member) {
        isActive = member;
    }
}
