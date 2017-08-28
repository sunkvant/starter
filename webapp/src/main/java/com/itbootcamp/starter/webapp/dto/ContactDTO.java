package com.itbootcamp.starter.webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itbootcamp.starter.datamodel.LocationEntity;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ContactDTO {

    @NotNull
    private String fullName;
    @NotNull
    private Timestamp dateOfBirth;
    @NotNull
    private String avatarPath;
    @NotNull
    private String phone;
    @NotNull
    private String skype;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String about;
    @NotNull
    private LocationDTO location;


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

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
