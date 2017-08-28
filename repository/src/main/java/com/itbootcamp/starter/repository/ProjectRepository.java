package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.LanguageEntity;
import com.itbootcamp.starter.datamodel.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity,Integer> {

    List<ProjectEntity> findByLanguages(List<LanguageEntity> languages);



}
