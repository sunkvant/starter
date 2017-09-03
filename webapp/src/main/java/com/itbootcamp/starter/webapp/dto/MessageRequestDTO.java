package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by admin on 9/4/2017.
 */
public class MessageRequestDTO {
    @NotNull
    private Integer receiverId;
    private String title;
    private String text;

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
