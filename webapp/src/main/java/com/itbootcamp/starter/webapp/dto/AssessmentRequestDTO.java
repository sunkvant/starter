package com.itbootcamp.starter.webapp.dto;

/**
 * Created by admin on 9/3/2017.
 */
public class AssessmentRequestDTO extends AbstractRequestDTO{
    private Boolean isAnswered;

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }
}
