package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.DirectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface DirectionRepository extends CrudRepository<DirectionEntity,Integer>{
    DirectionEntity getByNameIgnoreCase(String name);
}
