package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.ProjectStatusEntity;
import com.itbootcamp.starter.services.impl.ProjectStatusService;

import java.util.List;

/**
 * Created by admin on 8/24/2017.
 */
public interface IProjectStatusService {
    ProjectStatusEntity getByName(String name);
    ProjectStatusEntity getById(Integer id);
    List<ProjectStatusEntity> getAll();
}
