package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.CountryEntity;

public interface ICoutnryService {

    CountryEntity getByName(String name);

}
