package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.ProjectStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface ProjectStatusEntityRepository extends CrudRepository<ProjectStatusEntity,Long> {

}
