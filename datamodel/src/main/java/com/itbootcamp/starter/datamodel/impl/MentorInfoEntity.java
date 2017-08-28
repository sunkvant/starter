package com.itbootcamp.starter.datamodel.impl;


import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "mentor_info")
public class MentorInfoEntity extends AbstractEntityID{
    private Boolean isMentorExp;
    private String experience;
    private ProfileEntity profile;

    @Column(name = "is_mentor_exp")
    public Boolean getMentorExp() {
        return isMentorExp;
    }

    public void setMentorExp(Boolean mentorExp) {
        isMentorExp = mentorExp;
    }

    @Column(name = "experience", nullable = false, length = 255)
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
}
