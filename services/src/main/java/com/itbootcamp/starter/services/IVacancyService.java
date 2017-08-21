package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.VacancyEntity;

import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
public interface IVacancyService {

    VacancyEntity getById(Integer vacancyId);
    List<VacancyEntity> getAllByProjectId(Integer projectId);


}
