package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.PositionEntity;
import com.itbootcamp.starter.datamodel.impl.TeamEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.TeamRepository;
import com.itbootcamp.starter.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public PersonEntity getById(Integer personId) {
        return personRepository.findOne(personId);
    }

    @Override
    public List<PersonEntity> getAllPersonsByProjectId(Integer projectId) {

        List<PersonEntity> personEntities = new ArrayList<>();

        List<TeamEntity> teamEntities = teamRepository.getAllByProjectId(projectId);

        for (int i = 0; i < teamEntities.size(); i++) {

            personEntities.add(personRepository.findOne(teamEntities.get(i).getPerson().getId()));
        }

        return personEntities;
    }

    @Override
    public List<PersonEntity> getAllPersonsByProjectId(Integer projectId, Boolean isMember) {

        List<PersonEntity> personEntities = new ArrayList<>();

        List<TeamEntity> teamEntities = teamRepository.getAllByProjectIdAndMember(projectId, isMember);

        for (int i = 0; i < teamEntities.size(); i++) {

            personEntities.add(personRepository.findOne(teamEntities.get(i).getPerson().getId()));
        }

        return personEntities;
    }

    @Override
    public PositionEntity getPositionOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId) {
        PositionEntity positionEntity = new PositionEntity();

        TeamEntity teamEntity = teamRepository.getByPersonIdAndProjectId(personId, projectId);

        return teamEntity.getPosition();
    }

    @Override
    public Boolean getStatusOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId) {

        TeamEntity teamEntity = teamRepository.getByPersonIdAndProjectId(personId, projectId);

        return teamEntity.getMember();
    }


}
