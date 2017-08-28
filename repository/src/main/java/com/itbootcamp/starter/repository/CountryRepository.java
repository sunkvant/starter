package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity,Integer> {
}
