package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.CountryEntity;

import java.util.List;

public interface ICoutnryService {

    CountryEntity getByName(String name);
    List<CountryEntity> getAll();

}
