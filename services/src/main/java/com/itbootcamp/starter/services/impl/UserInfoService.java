package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IUserInfoService;
import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 7/28/2017.
 */
@Service
public class UserInfoService implements IUserInfoService {

    @Autowired private RoleRepository roleRepository;

    @Autowired private ProfileRepository profileRepository;

    @Autowired private DirectionRepository directionRepository;

    @Autowired private EducationRepository educationRepository;

    @Autowired private CourseRepository courseRepository;

    @Autowired private WorkPlaceRepository workPlaceRepository;

    //@Override
    public void register(RoleEntity roleEntity, ProfileEntity profileEntity, DirectionEntity directionEntity,
                         List<EducationEntity> educationEntityList, List<CourseEntity> courseEntityList, List<WorkplaceEntity> workplaceEntityList) {

        profileEntity.setDirection(directionRepository.save(directionEntity));

        //userInfoEntity.setProfileById(profileRepository.save(profileEntity));
        //userInfoEntity.setRole(roleRepository.save(roleEntity).getId());


        // TODO: 7/28/2017 set fields UserInfoEntity
        //authenticationRepository.save(userInfoEntity);

    }

    public void test(){

        ProfileEntity profile = new ProfileEntity();
        DirectionEntity direction = new DirectionEntity();

        direction.setName("Programmer");
        profile.setId(123);
        direction.setId(2);
        profile.setDirection(direction);
       /* profile.setFullName("kazachenko");
        profile.setPhone("8380030");
        profile.setAbout("hhh");
        profile.setAvatar("dfdf");
        profile.setSkype("razdvatri");
*/
        profileRepository.save(profile);
    }
}
