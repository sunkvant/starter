package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by admin on 8/16/2017.
 */
@Repository
public interface TeamRepository extends CrudRepository<TeamEntity,Integer> {

    List<TeamEntity> getAllByPersonId(Integer personId);
    List<TeamEntity> getAllByProjectId(Integer projectId);
    List<TeamEntity> getAllByPersonIdAndMember(Integer personId,Boolean isMember);
    List<TeamEntity> getAllByProjectIdAndMember(Integer projectId, Boolean isMember);
    TeamEntity getByPersonIdAndProjectId(Integer personId, Integer projectId);


}
