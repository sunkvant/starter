package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity,Integer> {

    CountryEntity getByNameIgnoreCase(String name);

}
