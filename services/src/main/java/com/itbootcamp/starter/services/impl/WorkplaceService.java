package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.WorkplaceEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.WorkPlaceRepository;
import com.itbootcamp.starter.services.IWorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkplaceService implements IWorkplaceService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WorkPlaceRepository workPlaceRepository;

    @Override
    public Boolean add(WorkplaceEntity workplaceEntity, PersonEntity personEntity) {

        if (personEntity.getProfile() == null) {

            return false;

        }


        workplaceEntity.setId(null);
        workplaceEntity.setProfile(personEntity.getProfile());
        personEntity.getProfile().getWorkplaces().add(workplaceEntity);

        if (personRepository.save(personEntity) != null) {

            return true;

        }

        return false;
    }

    @Override
    public Boolean update(WorkplaceEntity workplaceEntity, PersonEntity personEntity) {

        if ((personEntity.getProfile() == null)||(workplaceEntity.getId()==null)) {

            return false;

        }


        for (WorkplaceEntity workplace:personEntity.getProfile().getWorkplaces()) {

            if (workplaceEntity.getId().equals(workplace.getId()) ) {


                workplace.setCompany(workplaceEntity.getCompany());
                workplace.setSphereOfActivity(workplaceEntity.getSphereOfActivity());
                workplace.setPosition(workplaceEntity.getPosition());
                workplace.setDuties(workplaceEntity.getDuties());
                workplace.setStartWork(workplaceEntity.getStartWork());
                workplace.setEndWork(workplaceEntity.getEndWork());
                workplace.setWorking(workplaceEntity.getWorking());

                if (personRepository.save(personEntity) != null) {

                    return true;

                }
            }
        }


        return false;
    }

    @Override
    public Boolean delete(WorkplaceEntity workplaceEntity, PersonEntity personEntity) {

        for (WorkplaceEntity workplace:personEntity.getProfile().getWorkplaces()) {

            if (workplaceEntity.getId().equals(workplace.getId()) ) {

                personEntity.getProfile().getWorkplaces().remove(workplace);
                if (personRepository.save(personEntity)!=null) {

                    return true;

                }

            }

        }


        return false;
    }

    @Override
    public WorkplaceEntity getById(Integer workplaceId) {
        return workPlaceRepository.findOne(workplaceId);
    }

    @Override
    public List<WorkplaceEntity> getAll() {
        return (List) workPlaceRepository.findAll();
    }

}
