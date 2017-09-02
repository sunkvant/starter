package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by admin on 9/2/2017.
 */
public class AbstractRequestDTO {
    private Boolean isRead;
    @NotNull
    private Timestamp date;
    private Boolean senderVisible;
    private Boolean receiverVisible;
    private String requestType;
    private ProfileDTO senderPerson;
    private ProfileDTO receiverPerson;

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Boolean getSenderVisible() {
        return senderVisible;
    }

    public void setSenderVisible(Boolean senderVisible) {
        this.senderVisible = senderVisible;
    }

    public Boolean getReceiverVisible() {
        return receiverVisible;
    }

    public void setReceiverVisible(Boolean receiverVisible) {
        this.receiverVisible = receiverVisible;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ProfileDTO getSenderPerson() {
        return senderPerson;
    }

    public void setSenderPerson(ProfileDTO senderPerson) {
        this.senderPerson = senderPerson;
    }

    public ProfileDTO getReceiverPerson() {
        return receiverPerson;
    }

    public void setReceiverPerson(ProfileDTO receiverPerson) {
        this.receiverPerson = receiverPerson;
    }
}
