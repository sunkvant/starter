package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.CountryEntity;
import com.itbootcamp.starter.repository.CountryRepository;
import com.itbootcamp.starter.services.ICoutnryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICoutnryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryEntity getByName(String name) {
        return countryRepository.getByNameIgnoreCase(name);
    }

    @Override
    public List<CountryEntity> getAll() {
        return (List)countryRepository.findAll();
    }
}
