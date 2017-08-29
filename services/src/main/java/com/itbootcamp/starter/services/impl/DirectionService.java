package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.DirectionEntity;
import com.itbootcamp.starter.repository.DirectionRepository;
import com.itbootcamp.starter.services.IDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 7/28/2017.
 */
@Service
public class DirectionService implements IDirectionService {

    @Autowired
    private DirectionRepository directionRepository;


    @Override
    public DirectionEntity getByName(String name) {
        return directionRepository.getByNameIgnoreCase(name);
    }
}
