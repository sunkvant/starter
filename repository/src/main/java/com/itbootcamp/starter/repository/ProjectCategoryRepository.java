package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.ProjectCategoryEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by admin on 8/23/2017.
 */
public interface ProjectCategoryRepository extends CrudRepository<ProjectCategoryEntity, Integer>{
    ProjectCategoryEntity findByCategoryIgnoreCase(String category);
}
