package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;

/**
 * Created by admin on 9/3/2017.
 */
public interface IVacancyRequestService {
    Boolean save(Integer vacancyId, PersonEntity personEntity);

    Boolean answer(Integer vacancyRequestId, PersonEntity personEntity);
}
