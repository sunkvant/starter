package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by admin on 8/16/2017.
 */
@Repository
public interface TeamRepository extends CrudRepository<TeamEntity,Integer> {

    List<TeamEntity> findAllByPersonId(Integer personId);
    List<TeamEntity> findAllByProjectId(Integer projectId);
    List<TeamEntity> findAllByPersonIdAndMember(Integer personId, Boolean isMember);
    List<TeamEntity> findAllByProjectIdAndMember(Integer projectId, Boolean isMember);
    TeamEntity findByPersonIdAndProjectId(Integer personId, Integer projectId);


}
