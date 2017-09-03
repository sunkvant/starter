package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by admin on 9/2/2017.
 */
public class MessageRequestDTO extends AbstractRequestDTO {

    private String title;
    private String text;

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
