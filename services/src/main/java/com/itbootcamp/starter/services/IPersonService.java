package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.ContactEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.PositionEntity;

import java.util.List;


/**
 * Created by admin on 8/16/2017.
 */
public interface IPersonService {

    PersonEntity getById(Integer personId);
    PersonEntity getByLogin(String login);
    List<PersonEntity> getAllPersonsByProjectId(Integer projectId);
    List<PersonEntity> getAllPersonsByProjectId(Integer projectId, Boolean isMember);
    PositionEntity getPositionOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId);
    Boolean getStatusOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId);
    Boolean create(PersonEntity personEntity);
    Boolean update(PersonEntity personEntity, ContactEntity contactEntity);

    List<PersonEntity> searchPersons(
            String role,
            String fullName,
            Integer ageFrom,
            Integer ageTo,
            String country,
            String city,
            List<String> direction,
            List<String> skills,
            String educationName,
            Boolean isMentorExp);


}


