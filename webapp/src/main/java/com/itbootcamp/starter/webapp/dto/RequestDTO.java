package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by admin on 9/3/2017.
 */
public class RequestDTO {
    private Integer id;
    private Boolean isRead;
    @NotNull
    private Timestamp date;
    private Boolean senderVisible;
    private Boolean receiverVisible;
    private String requestType;
    private ProfileDTO senderPerson;
    private ProfileDTO receiverPerson;
    private String title;
    private Boolean isSingle;
    private ProjectDTO projectDTO;
    private String text;
    private Boolean isAnswered;
    private VacancyDTO vacancyDTO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSingle() {
        return isSingle;
    }

    public void setSingle(Boolean single) {
        isSingle = single;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public VacancyDTO getVacancyDTO() {
        return vacancyDTO;
    }

    public void setVacancyDTO(VacancyDTO vacancyDTO) {
        this.vacancyDTO = vacancyDTO;
    }
}
