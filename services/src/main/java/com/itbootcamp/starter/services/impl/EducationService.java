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
    public void addEducation(EducationEntity educationEntity) {

        profileRepository.exists(educationEntity.getProfile().getId());


    }

    @Override
    public List<EducationEntity> getAllByprofile(ProfileEntity profileEntity) {

       return educationRepository.findAllByProfile(profileEntity);

    }

    @Override
    public void update(EducationEntity educationEntity) {
        educationRepository.save(educationEntity);
    }

}
