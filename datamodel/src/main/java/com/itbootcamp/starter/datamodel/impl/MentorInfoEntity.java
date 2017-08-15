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
    private PersonEntity person;

    @Column(name = "is_mentor_exp", nullable = false)
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
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
