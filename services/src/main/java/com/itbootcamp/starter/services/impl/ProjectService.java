package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import com.itbootcamp.starter.datamodel.impl.TeamEntity;
import com.itbootcamp.starter.repository.ProjectRepository;
import com.itbootcamp.starter.repository.TeamRepository;
import com.itbootcamp.starter.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectEntity getById(Integer projectId) {



        return projectRepository.findOne(projectId);

    }

    @Override
    public List<ProjectEntity> getAllProjectsByPersonId(Integer personId) {

        List<TeamEntity> teamEntities=teamRepository.getAllByPersonId(personId);
        List<ProjectEntity> projectEntityList=new ArrayList<>();


        for(int i=0; i<teamEntities.size(); i++) {

            projectEntityList.add(projectRepository.findOne(teamEntities.get(i).getProject().getId()));


        }

        return projectEntityList;
    }
}
