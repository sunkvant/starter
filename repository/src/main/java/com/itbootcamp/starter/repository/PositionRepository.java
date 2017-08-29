package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.PositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
public interface PositionRepository extends CrudRepository<PositionEntity,Integer> {

    PositionEntity getByNameIgnoreCase(String name);

}
