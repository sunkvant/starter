package com.itbootcamp.starter.datamodel.impl;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.itbootcamp.starter.datamodel.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "person")
public class PersonEntity extends AbstractEntityID implements UserDetails {
    private String login;
    private String password;
    private Boolean isBlocked;
    private ContactEntity contact;
    private RoleEntity role;
    private ProfileEntity profile;
    private List<ProjectEntity> customerProjects;
    private List<RequestEntity> senderRequests;
    private List<RequestEntity> receiverRequests;
    private List<ReviewEntity> receiverReviews;
    private List<ReviewEntity> senderReviews;
    private List<TeamEntity> teams;

    @Column(name = "login", nullable = false, length = 255)
    public String getLogin() {
        return login;
    }

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
//--------------------UserDetails----------------------------------

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Transient
    @Override
    public String getUsername() {
        return login;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }
//---------------------------------------------------------------------
    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(mappedBy = "person",fetch = FetchType.LAZY)
    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @OneToOne(mappedBy = "persons",fetch = FetchType.LAZY)
    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    public List<ProjectEntity> getCustomerProjects() {
        return customerProjects;
    }

    public void setCustomerProjects(List<ProjectEntity> customerProjects) {
        this.customerProjects = customerProjects;
    }

    @OneToMany(mappedBy = "senderPerson",fetch = FetchType.LAZY)
    public List<RequestEntity> getSenderRequests() {
        return senderRequests;
    }

    public void setSenderRequests(List<RequestEntity> senderRequests) {
        this.senderRequests = senderRequests;
    }

    @OneToMany(mappedBy = "receiverPerson",fetch = FetchType.LAZY)
    public List<RequestEntity> getReceiverRequests() {
        return receiverRequests;
    }

    public void setReceiverRequests(List<RequestEntity> receiverRequests) {
        this.receiverRequests = receiverRequests;
    }

    @OneToMany(mappedBy = "receiverPerson",fetch = FetchType.LAZY)
    public List<ReviewEntity> getReceiverReviews() {
        return receiverReviews;
    }

    public void setReceiverReviews(List<ReviewEntity> receiverReviews) {
        this.receiverReviews = receiverReviews;
    }

    @OneToMany(mappedBy = "senderPerson",fetch = FetchType.LAZY)
    public List<ReviewEntity> getSenderReviews() {
        return senderReviews;
    }

    public void setSenderReviews(List<ReviewEntity> senderReviews) {
        this.senderReviews = senderReviews;
    }

    @OneToMany(mappedBy = "person",fetch = FetchType.LAZY)
    public List<TeamEntity> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamEntity> teams) {
        this.teams = teams;
    }
}
