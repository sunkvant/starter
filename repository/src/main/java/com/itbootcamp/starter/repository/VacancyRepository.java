package com.itbootcamp.starter.repository;
import com.itbootcamp.starter.datamodel.VacancyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sergei on 7/21/2017.
 */
@Repository
public interface VacancyRepository extends CrudRepository<VacancyEntity,Integer> {
    List<VacancyEntity> findByProjectId(Integer projectId);
    List<VacancyEntity> findByPersonNumberBetween(Integer after, Integer before);
}
