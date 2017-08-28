package com.itbootcamp.starter.datamodel.impl;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "contact")
public class ContactEntity extends AbstractEntityID implements Serializable {
    private String fullName;
    private Timestamp dateOfBirth;
    private String avatarPath;
    private String phone;
    private String skype;
    private String email;
    private String about;
    private PersonEntity person;
    private LocationEntity location;


  //  @GenericGenerator(name = "generator", strategy = "foreign",
   //         parameters = @Parameter(name = "property", value = "person"))

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
    //@PrimaryKeyJoinColumn
    @MapsId
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    public LocationEntity getLocation() {
        return location;
    }
    public void setLocation(LocationEntity location) {
        this.location = location;
    }

}