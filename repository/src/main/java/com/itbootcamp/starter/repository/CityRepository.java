package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.CityEntity;
import com.itbootcamp.starter.datamodel.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<CityEntity,Integer> {

    CityEntity getByNameIgnoreCase(String name);

    List<CityEntity> findAllByCountryId(Integer countryId);

}
