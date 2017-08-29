package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.CityEntity;
import com.itbootcamp.starter.repository.CityRepository;
import com.itbootcamp.starter.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityEntity getByName(String name) {
        return cityRepository.getByNameIgnoreCase(name);
    }

    @Override
    public List<CityEntity> getAll() {
        return (List) cityRepository.findAll();
    }

    @Override
    public List<CityEntity> getAllByCountryId(Integer countryId) {
        return (List) cityRepository.findAllByCountryId(countryId);
    }
}
