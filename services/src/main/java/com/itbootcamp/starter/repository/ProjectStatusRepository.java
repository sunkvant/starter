package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.ProjectStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 8/24/2017.
 */
@Repository
public interface ProjectStatusRepository extends CrudRepository<ProjectStatusEntity, Integer>{
    ProjectStatusEntity getByStatusIgnoreCase(String status);
}
