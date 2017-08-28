package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.VacancyRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sergei on 7/21/2017.
 */
@Repository
public interface VacancyRequestEntityRepository extends CrudRepository<VacancyRequestEntity,Long> {

}
