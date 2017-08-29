package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.ProjectEntity;
import com.itbootcamp.starter.datamodel.VacancyEntity;

import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
public interface IVacancyService {

    VacancyEntity getById(Integer vacancyId);
    List<VacancyEntity> getAllByProjectId(Integer projectId);
    Boolean add(VacancyEntity vacancyEntity, ProjectEntity projectEntity);
    Boolean update(VacancyEntity vacancyEntity, ProjectEntity projectEntity);
    Boolean delete(VacancyEntity vacancyEntity,ProjectEntity projectEntity);


    List<VacancyEntity> searchVacancies(List<String> positions, String role, List<String> skills, List<String> languages);
}
