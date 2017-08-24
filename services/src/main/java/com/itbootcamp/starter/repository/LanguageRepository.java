package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.LanguageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 8/24/2017.
 */
@Repository
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer>{
    LanguageEntity getByNameIgnoreCase(String name);
}
