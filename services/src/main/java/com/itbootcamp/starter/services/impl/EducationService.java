package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.repository.EducationRepository;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.services.IEducationService;
import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 8/11/2017.
 */


@Service
public class EducationService implements IEducationService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EducationRepository educationRepository;


    @Override
    public Boolean add(EducationEntity educationEntity, PersonEntity personEntity) {

        if (personEntity.getProfile() == null) {

            return false;

        } else {

            educationEntity.setId(null);
            educationEntity.setProfile(personEntity.getProfile());

            if (educationRepository.save(educationEntity) != null) {

                return true;

            }

            return false;
        }
    }

    @Override
    public Boolean update(EducationEntity educationEntity,PersonEntity personEntity) {
        if (personEntity.getProfile() == null) {

            return false;

        } else {

            List<EducationEntity> educationEntities = personEntity.getProfile().getEducations();

            for (int i = 0; i < educationEntities.size(); i++) {

                if (educationEntity.getId() == educationEntities.get(i).getId()) {

                    educationEntity.setProfile(personEntity.getProfile());
                    if (educationRepository.save(educationEntity) != null) {

                        return true;

                    }

                }

            }

            return false;

        }
    }

}
