package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.WorkplaceEntity;

import java.util.List;

public interface IWorkplaceService {

    Boolean add(WorkplaceEntity workplaceEntity, PersonEntity personEntity);
    Boolean update(WorkplaceEntity workplaceEntity, PersonEntity personEntity);
    Boolean delete(WorkplaceEntity workplaceEntity, PersonEntity personEntity);
    WorkplaceEntity getById(Integer workplaceId);
    List<WorkplaceEntity> getAll();
}
