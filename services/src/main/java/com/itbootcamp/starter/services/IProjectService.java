package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ProjectEntity;

import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
public interface IProjectService {

    ProjectEntity getById(Integer projectId);
    List<ProjectEntity> getAllProjectsByPersonId(Integer personId);
    List<ProjectEntity> getAllProjectsByPersonId(Integer personId, Boolean isMember);
    Boolean isExist(Integer projectId);
    Boolean isMember(PersonEntity personEntity, ProjectEntity projectEntity);
    Boolean create(ProjectEntity projectEntity,PersonEntity personEntity);

    List<ProjectEntity> searchProjects(String projectName,
                                       List<String> projectCategoryList,
                                       List<String> projectStatusList,
                                       List<String> projectLanguageList);
}
