package com.itbootcamp.starter.webapp.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by admin on 8/21/2017.
 */
public class ReviewDTO {

    private Integer id;
    private Timestamp date;
    @Min(1)
    @Max(10)
    @NotNull
    private Integer rating;
    @NotBlank
    @NotNull
    private String text;
    private Integer senderPersonId;
    @NotNull
    private Integer receiverPersonId;
    @NotNull
    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderPersonId() {
        return senderPersonId;
    }

    public void setSenderPersonId(Integer senderPersonId) {
        this.senderPersonId = senderPersonId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getReceiverPersonId() {
        return receiverPersonId;
    }

    public void setReceiverPersonId(Integer receiverPersonId) {
        this.receiverPersonId = receiverPersonId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
