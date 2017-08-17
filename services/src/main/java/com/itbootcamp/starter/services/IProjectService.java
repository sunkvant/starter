package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.ProjectEntity;

import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
public interface IProjectService {

    ProjectEntity getById(Integer projectId);
    List<ProjectEntity> getAllProjectsByPersonId(Integer personId);
    List<ProjectEntity> getAllProjectsByPersonId(Integer personId, Boolean isMember);


}
