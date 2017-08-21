package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.VacancyEntity;
import com.itbootcamp.starter.repository.VacancyRepository;
import com.itbootcamp.starter.services.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
@Service
public class VacancyService implements IVacancyService {


    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public VacancyEntity getById(Integer vacancyId) {

        return vacancyRepository.findOne(vacancyId);

    }

    @Override
    public List<VacancyEntity> getAllByProjectId(Integer projectId) {

        return vacancyRepository.findByProjectId(projectId);
    }
}
