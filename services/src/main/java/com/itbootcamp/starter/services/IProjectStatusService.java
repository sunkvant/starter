package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.ProjectStatusEntity;

/**
 * Created by admin on 8/24/2017.
 */
public interface IProjectStatusService {
    ProjectStatusEntity getByName(String name);
}
