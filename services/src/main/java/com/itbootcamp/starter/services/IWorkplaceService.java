package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.WorkplaceEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CORBA.PERSIST_STORE;

public interface IWorkplaceService {

    Boolean add(WorkplaceEntity workplaceEntity, PersonEntity personEntity);
    Boolean update(WorkplaceEntity workplaceEntity, PersonEntity personEntity);
    Boolean delete(WorkplaceEntity workplaceEntity, PersonEntity personEntity);
    WorkplaceEntity getById(Integer workplaceId);
}
