package com.itbootcamp.starter.webapp.dto;

/**
 * Created by admin on 8/15/2017.
 */
public class MentorDTO extends AbstractMemberDTO {

    private Boolean isMentorExp;
    private String experience;

    public final Boolean getMentorExp() {
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
