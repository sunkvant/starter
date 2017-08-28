package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.CityEntity;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<CityEntity,Integer> {
}
