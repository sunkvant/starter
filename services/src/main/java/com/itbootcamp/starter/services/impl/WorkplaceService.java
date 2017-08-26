package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.WorkplaceEntity;
import com.itbootcamp.starter.repository.WorkPlaceRepository;
import com.itbootcamp.starter.services.IWorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkplaceService implements IWorkplaceService {

    @Autowired
    private WorkPlaceRepository workPlaceRepository;

    @Override
    public Boolean add(WorkplaceEntity workplaceEntity, PersonEntity personEntity) {
        if (personEntity.getProfile() == null) {

            return false;

        } else {

            workplaceEntity.setId(null);
            workplaceEntity.setProfile(personEntity.getProfile());

            if (workPlaceRepository.save(workplaceEntity) != null) {

                return true;

            }

            return false;
        }
    }

    @Override
    public Boolean update(WorkplaceEntity workplaceEntity, PersonEntity personEntity) {
        if (personEntity.getProfile() == null) {

            return false;

        } else {

            List<WorkplaceEntity> workplaceEntities = personEntity.getProfile().getWorkplaces();

            for (int i = 0; i < workplaceEntities.size(); i++) {

                if (workplaceEntity.getId() == workplaceEntities.get(i).getId()) {

                    workplaceEntity.setProfile(personEntity.getProfile());
                    if (workPlaceRepository.save(workplaceEntity) != null) {

                        return true;

                    }

                }

            }

            return false;

        }
    }

}
