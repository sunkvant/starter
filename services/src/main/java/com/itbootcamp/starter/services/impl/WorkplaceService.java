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


        for (int i = 0; i < personEntity.getProfile().getWorkplaces().size(); i++) {

            if (workplaceEntity.getId() == personEntity.getProfile().getWorkplaces().get(i).getId()) {


                personEntity.getProfile().getWorkplaces().get(i).setCompany(workplaceEntity.getCompany());
                personEntity.getProfile().getWorkplaces().get(i).setSphereOfActivity(workplaceEntity.getSphereOfActivity());
                personEntity.getProfile().getWorkplaces().get(i).setPosition(workplaceEntity.getPosition());
                personEntity.getProfile().getWorkplaces().get(i).setDuties(workplaceEntity.getDuties());
                personEntity.getProfile().getWorkplaces().get(i).setStartWork(workplaceEntity.getStartWork());
                personEntity.getProfile().getWorkplaces().get(i).setEndWork(workplaceEntity.getEndWork());
                personEntity.getProfile().getWorkplaces().get(i).setWorking(workplaceEntity.getWorking());

                if (personRepository.save(personEntity) != null) {

                    return true;

                }
            }
        }


        return false;
    }

    @Override
    public Boolean delete(WorkplaceEntity workplaceEntity, PersonEntity personEntity) {

        for (int i = 0; i < personEntity.getProfile().getWorkplaces().size(); i++) {

            if (workplaceEntity.getId()==personEntity.getProfile().getWorkplaces().get(i).getId()) {

                personEntity.getProfile().getWorkplaces().remove(i);
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
