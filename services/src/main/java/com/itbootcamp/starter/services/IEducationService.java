package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.EducationEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 8/11/2017.
 */
public interface IEducationService {

    EducationEntity getById(Integer educationId);
    Boolean add(EducationEntity educationEntity,PersonEntity personEntity);
    Boolean update(EducationEntity educationEntity,PersonEntity personEntity);
    Boolean delete(EducationEntity educationEntity,PersonEntity personEntity);
    List<EducationEntity> getAll();

}
