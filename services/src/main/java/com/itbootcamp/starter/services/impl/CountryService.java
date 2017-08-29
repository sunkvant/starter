package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.CountryEntity;
import com.itbootcamp.starter.repository.CountryRepository;
import com.itbootcamp.starter.services.ICoutnryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICoutnryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryEntity getByName(String name) {
        return countryRepository.getByNameIgnoreCase(name);
    }
}
