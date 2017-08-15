package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "review")
public class ReviewEntity  extends AbstractEntityID{
    private Timestamp date;
    private Integer rating;
    private String text;
    private PersonEntity receiverPerson;
    private PersonEntity senderPerson;
    private ProjectEntity project;

    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Column(name = "rating", nullable = false)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(name = "text", nullable = false, length = 255)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    public PersonEntity getReceiverPerson() {
        return receiverPerson;
    }

    public void setReceiverPerson(PersonEntity receiverPerson) {
        this.receiverPerson = receiverPerson;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    public PersonEntity getSenderPerson() {
        return senderPerson;
    }

    public void setSenderPerson(PersonEntity senderPerson) {
        this.senderPerson = senderPerson;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
