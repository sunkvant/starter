package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.repository.EducationRepository;
import com.itbootcamp.starter.repository.EducationTypeRepository;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.IEducationService;
import com.itbootcamp.starter.datamodel.EducationEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 8/11/2017.
 */


@Service
public class EducationService implements IEducationService {


    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EducationTypeRepository educationTypeRepository;


    @Override
    public EducationEntity getById(Integer educationId) {

        return educationRepository.findOne(educationId);

    }

    @Override
    public Boolean add(EducationEntity educationEntity, PersonEntity personEntity) {

        if (personEntity.getProfile() == null) {

            return false;

        }

        if (!educationTypeRepository.exists(educationEntity.getEducationTypeEntity().getId())) {

            return false;

        }

        educationEntity.setId(null);
        educationEntity.setProfile(personEntity.getProfile());
        personEntity.getProfile().getEducations().add(educationEntity);

        if (personRepository.save(personEntity) != null) {

            return true;

        }

        return false;
    }

    @Override
    public Boolean update(EducationEntity educationEntity,PersonEntity personEntity) {

        if ((personEntity.getProfile() == null)||(educationEntity.getId()==null)) {

            return false;

        }

        if (!educationTypeRepository.exists(educationEntity.getEducationTypeEntity().getId())) {

            return false;

        }


        for (int i = 0; i < personEntity.getProfile().getEducations().size(); i++) {

            if (educationEntity.getId() == personEntity.getProfile().getEducations().get(i).getId()) {


                personEntity.getProfile().getEducations().get(i).setName(educationEntity.getName());
                personEntity.getProfile().getEducations().get(i).setFaculty(educationEntity.getFaculty());
                personEntity.getProfile().getEducations().get(i).setGraduationYear(educationEntity.getGraduationYear());
                personEntity.getProfile().getEducations().get(i).setEducationTypeEntity(educationEntity.getEducationTypeEntity());


                if (personRepository.save(personEntity) != null) {

                    return true;

                }
            }
        }


        return false;
    }

    @Override
    public Boolean delete(EducationEntity educationEntity, PersonEntity personEntity) {

        for (int i = 0; i < personEntity.getProfile().getEducations().size(); i++) {

            if (educationEntity.getId()==personEntity.getProfile().getEducations().get(i).getId()) {

                personEntity.getProfile().getEducations().remove(i);
                if (personRepository.save(personEntity)!=null) {

                    return true;

                }

            }

        }


        return false;
    }

}
