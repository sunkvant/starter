package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.ProjectCategoryEntity;

import java.util.List;

/**
 * Created by admin on 8/23/2017.
 */
public interface IProjectCategoryService {
    ProjectCategoryEntity getById(Integer id);

    ProjectCategoryEntity getByName(String name);
    List<ProjectCategoryEntity> getAll();
}
