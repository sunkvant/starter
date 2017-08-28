package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.WorkplaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sergei on 7/21/2017.
 */
@Repository
public interface WorkPlaceRepository extends CrudRepository<WorkplaceEntity,Integer> {
    WorkplaceEntity findByProfileId(Integer profileId);
    List<WorkplaceEntity> findByCompany(String company);
    List<WorkplaceEntity> findByPosition(String position);
}
