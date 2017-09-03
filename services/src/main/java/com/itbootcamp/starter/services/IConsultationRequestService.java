package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;

/**
 * Created by admin on 9/3/2017.
 */
public interface IConsultationRequestService {
    Boolean save(Integer projectId, String title, Boolean isSingle, Integer receiverPersonId, PersonEntity personEntity);
}
