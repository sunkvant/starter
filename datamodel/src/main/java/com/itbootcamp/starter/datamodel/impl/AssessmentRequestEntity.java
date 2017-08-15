package com.itbootcamp.starter.datamodel.impl;

import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "assessment_request")
public class AssessmentRequestEntity extends RequestEntity{
    private Boolean isAnswered;

    @Column(name = "is_answered", nullable = false)
    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

}
