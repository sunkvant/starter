package com.itbootcamp.starter.datamodel.impl;

import com.itbootcamp.starter.datamodel.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "person")
public class PersonEntity extends AbstractEntityID
                          implements IAdminEntity, IModerEntity, ICustomerEntity, IMentorEntity, ITraineeEntity {
    private String login;
    private String password;
    private Boolean isBlocked;
    private ContactEntity contact;
    private RoleEntity role;
    private ProfileEntity profile;
    private List<ProjectEntity> projects;
    private List<RequestEntity> senderRequests;
    private List<RequestEntity> receiverRequests;
    private List<ReviewEntity> receiverReviews;
    private List<ReviewEntity> senderReviews;
    private List<TeamEntity> teams;
    private List<SkillEntity> skills;
    private MentorInfoEntity mentorInfo;

    @Override
    @Column(name = "login", nullable = false, length = 255)
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "is_blocked", nullable = false)
    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @OneToOne(mappedBy = "person",fetch = FetchType.LAZY)
    public ContactEntity getContact() {
        return contact;
    }

    @Override
    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public RoleEntity getRole() {
        return role;
    }

    @Override
    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Override
    @OneToOne(mappedBy = "person",fetch = FetchType.LAZY)
    public ProfileEntity getProfile() {
        return profile;
    }

    @Override
    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    @Override
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    public List<ProjectEntity> getProjects() {
        return projects;
    }

    @Override
    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }

    @Override
    @OneToMany(mappedBy = "senderPerson",fetch = FetchType.LAZY)
    public List<RequestEntity> getSenderRequests() {
        return senderRequests;
    }

    @Override
    public void setSenderRequests(List<RequestEntity> senderRequests) {
        this.senderRequests = senderRequests;
    }

    @Override
    @OneToMany(mappedBy = "receiverPerson",fetch = FetchType.LAZY)
    public List<RequestEntity> getReceiverRequests() {
        return receiverRequests;
    }

    @Override
    public void setReceiverRequests(List<RequestEntity> receiverRequests) {
        this.receiverRequests = receiverRequests;
    }

    @Override
    @OneToMany(mappedBy = "receiverPerson",fetch = FetchType.LAZY)
    public List<ReviewEntity> getReceiverReviews() {
        return receiverReviews;
    }

    @Override
    public void setReceiverReviews(List<ReviewEntity> receiverReviews) {
        this.receiverReviews = receiverReviews;
    }

    @Override
    @OneToMany(mappedBy = "senderPerson",fetch = FetchType.LAZY)
    public List<ReviewEntity> getSenderReviews() {
        return senderReviews;
    }

    @Override
    public void setSenderReviews(List<ReviewEntity> senderReviews) {
        this.senderReviews = senderReviews;
    }

    @Override
    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    public List<TeamEntity> getTeams() {
        return teams;
    }

    @Override
    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }

    @Override
    @ManyToMany(mappedBy = "persons",fetch = FetchType.LAZY)
    public List<SkillEntity> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    @Override
    @OneToOne(mappedBy = "person",fetch = FetchType.LAZY)
    public MentorInfoEntity getMentorInfo() {
        return mentorInfo;
    }

    @Override
    public void setMentorInfo(MentorInfoEntity mentorInfo) {
        this.mentorInfo = mentorInfo;
    }
}
