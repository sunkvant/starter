package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import com.itbootcamp.starter.datamodel.impl.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface EducationRepository extends CrudRepository<EducationEntity,Integer>{
    List<EducationEntity> findByProfileId(int profileId);
    List<EducationEntity> findAllByProfile(ProfileEntity profileEntity);
}
