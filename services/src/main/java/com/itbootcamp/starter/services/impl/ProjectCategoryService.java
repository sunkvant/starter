package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.ProjectCategoryEntity;
import com.itbootcamp.starter.repository.ProjectCategoryRepository;
import com.itbootcamp.starter.services.IProjectCategoryService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 8/23/2017.
 */
@Service
public class ProjectCategoryService implements IProjectCategoryService {

    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;

    @Override
    public ProjectCategoryEntity getById(Integer id) {
        return projectCategoryRepository.findOne(id);
    }

    @Override
    public ProjectCategoryEntity getByName(String name){
        return projectCategoryRepository.findByCategoryIgnoreCase(name);
    }
}
