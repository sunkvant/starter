package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.repository.EducationRepository;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.IEducationService;
import com.itbootcamp.starter.datamodel.EducationEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 8/11/2017.
 */


@Service
public class EducationService implements IEducationService {


    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public EducationEntity getById(Integer educationId) {

        return educationRepository.findOne(educationId);

    }

    @Override
    public Boolean add(EducationEntity educationEntity, PersonEntity personEntity) {

        if (personEntity.getProfile() == null) {

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


        for (EducationEntity education:personEntity.getProfile().getEducations()) {

            if (educationEntity.getId().equals(education.getId())) {


                education.setName(educationEntity.getName());
                education.setFaculty(educationEntity.getFaculty());
                education.setGraduationYear(educationEntity.getGraduationYear());


                if (personRepository.save(personEntity) != null) {

                    return true;

                }
            }
        }


        return false;
    }

    @Override
    public Boolean delete(EducationEntity educationEntity, PersonEntity personEntity) {

        for (EducationEntity education:personEntity.getProfile().getEducations()) {

            if (educationEntity.getId().equals(education.getId())) {

                personEntity.getProfile().getEducations().remove(education);
                if (personRepository.save(personEntity)!=null) {

                    return true;

                }

            }

        }


        return false;
    }

    @Override
    public List<EducationEntity> getAll() {
        return (List)educationRepository.findAll();
    }

}
