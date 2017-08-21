package com.itbootcamp.starter.webapp.dto;

/**
 * Created by admin on 8/17/2017.
 */
public class MemberDTO {

    private ProfileDTO profile;
    private PositionDTO position;
    private RoleDTO role;
    private Boolean isActive;

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public ProfileDTO getProfile() {
        return profile;
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
