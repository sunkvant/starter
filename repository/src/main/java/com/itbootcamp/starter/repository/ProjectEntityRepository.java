package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface ProjectEntityRepository extends CrudRepository<ProjectEntity,Long> {
    ProjectEntity findByCustomerId(int id);
    List<ProjectEntity> findByNameContains(String containsWord);
    List<ProjectEntity> findByDateStartBetween(Timestamp dateMin,Timestamp dateMax);
    List<ProjectEntity> findByDateEndBetween(Timestamp dateMin,Timestamp dateMax);
    List<ProjectEntity> findByProjectStatusId(int id);
}
