package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.ProfileEntity;

import java.util.List;

/**
 * Created by admin on 8/11/2017.
 */
public interface IEducationService {

    EducationEntity getById(Integer educationId);
    Boolean add(EducationEntity educationEntity,PersonEntity personEntity);
    Boolean update(EducationEntity educationEntity,PersonEntity personEntity);
    Boolean delete(EducationEntity educationEntity,PersonEntity personEntity);

}
