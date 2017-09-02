package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestEntity;

import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
public interface IRequestService {
    List<RequestEntity> getAll(PersonEntity personEntity);

    List<RequestEntity> getReceivedRequests(PersonEntity personEntity);

    List<RequestEntity> getSentRequests(PersonEntity personEntity);

    Integer getCountNotRead(PersonEntity personEntity);
}
