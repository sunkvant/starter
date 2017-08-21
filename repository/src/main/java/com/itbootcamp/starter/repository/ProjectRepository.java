package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity,Integer> {
    List<ProjectEntity> findAllByProjectCategory

    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public List<ProjectEntity> find(@Param("lastName") String lastName);


}
