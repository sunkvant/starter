package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.ProjectStatusEntity;
import com.itbootcamp.starter.repository.ProjectStatusRepository;
import com.itbootcamp.starter.services.IProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 8/24/2017.
 */
@Service
public class ProjectStatusService implements IProjectStatusService {
    @Autowired
    private ProjectStatusRepository projectStatusRepository;
    @Override
    public ProjectStatusEntity getByName(String name) {
        return projectStatusRepository.getByStatusIgnoreCase(name);
    }

    @Override
    public ProjectStatusEntity getById(Integer id) {
        return projectStatusRepository.findOne(id);
    }
}
