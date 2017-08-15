package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "request")
public class RequestEntity extends AbstractEntityID{
    protected Boolean isRead;
    protected Timestamp date;
    protected Boolean senderVisible;
    protected Boolean receiverVisible;
    protected RequestTypeEntity requestType;
    protected PersonEntity senderPerson;
    protected PersonEntity receiverPerson;

    @Column(name = "is_read", nullable = false)
    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Column(name = "sender_visible", nullable = false)
    public Boolean getSenderVisible() {
        return senderVisible;
    }

    public void setSenderVisible(Boolean senderVisible) {
        this.senderVisible = senderVisible;
    }

    @Column(name = "receiver_visible", nullable = false)
    public Boolean getReceiverVisible() {
        return receiverVisible;
    }

    public void setReceiverVisible(Boolean receiverVisible) {
        this.receiverVisible = receiverVisible;
    }

    @ManyToOne
    @JoinColumn(name = "request_type_id", referencedColumnName = "id", nullable = false)
    public RequestTypeEntity getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestTypeEntity requestType) {
        this.requestType = requestType;
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
    @JoinColumn(name = "receiver_id", referencedColumnName = "id", nullable = false)
    public PersonEntity getReceiverPerson() {
        return receiverPerson;
    }

    public void setReceiverPerson(PersonEntity receiverPerson) {
        this.receiverPerson = receiverPerson;
    }
}
