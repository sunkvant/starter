package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.RequestTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 9/3/2017.
 */
@Repository
public interface RequestTypeRepository extends CrudRepository<RequestTypeEntity, Integer>{
    RequestTypeEntity findByType(String type);
}
