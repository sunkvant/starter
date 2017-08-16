package com.itbootcamp.starter.datamodel.impl;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "contact")
public class ContactEntity extends AbstractEntityID{
    @Expose private String fullName;
    @Expose private Timestamp dateOfBirth;
    @Expose private String avatarPath;
    @Expose private String phone;
    @Expose private String skype;
    @Expose private String email;
    @Expose private String about;
    private PersonEntity person;

    @Column(name = "full_name", nullable = false, length = 255)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "date_of_birth", nullable = false)
    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "avatar_path", nullable = false, length = 255)
    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Column(name = "phone", nullable = false, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "skype", nullable = false, length = 255)
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "about", nullable = false, length = 255)
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
