package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.CityEntity;
import com.itbootcamp.starter.datamodel.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<CityEntity,Integer> {

    CityEntity getByNameIgnoreCase(String name);

    List<CityEntity> findAllByCountry(CountryEntity countryEntity);

}
