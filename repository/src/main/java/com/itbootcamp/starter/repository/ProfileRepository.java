package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {

}
