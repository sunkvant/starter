package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.PositionEntity;
import com.itbootcamp.starter.repository.PositionRepository;
import com.itbootcamp.starter.services.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public PositionEntity getByName(String name) {
        return positionRepository.getByNameIgnoreCase(name);
    }

    @Override
    public List<PositionEntity> getAll() {
        return (List)positionRepository.findAll();
    }
}
