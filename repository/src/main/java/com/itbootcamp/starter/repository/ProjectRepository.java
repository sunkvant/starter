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
public interface ProjectRepository extends CrudRepository<ProjectEntity,Integer> {


}
