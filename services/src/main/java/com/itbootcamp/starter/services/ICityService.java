package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.CityEntity;

import java.util.List;

public interface ICityService {

    CityEntity getByName(String name);
    List<CityEntity> getAll();
    List<CityEntity> getAllByCountryId(Integer countryId);

}
