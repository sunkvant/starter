package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.PositionEntity;

import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
public interface IPersonService {

    PersonEntity getById(Integer personId);
    List<PersonEntity> getAllPersonsByProjectId(Integer projectId);
    List<PersonEntity> getAllPersonsByProjectId(Integer projectId, Boolean isMember);
    PositionEntity getPositionByPersonIdAndByProjectId(Integer personId, Integer projectId);

}
