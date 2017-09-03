package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;

/**
 * Created by admin on 9/3/2017.
 */
public interface IAssessmentRequestService {
    Boolean save(Integer receiverId, PersonEntity personEntity);

    Boolean answer(Integer assessmentRequest, PersonEntity personEntity);
}
