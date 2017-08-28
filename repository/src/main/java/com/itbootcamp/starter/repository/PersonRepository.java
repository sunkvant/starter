package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 8/9/2017.
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

    PersonEntity findByLoginAndPassword(String login, String password);
    PersonEntity findByRole(RoleEntity roleEntity);
    PersonEntity findByLogin(String login);
}
