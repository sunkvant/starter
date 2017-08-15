package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.DirectionEntity;
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
    public List<DirectionEntity> getAll() {
        return (List<DirectionEntity>) directionRepository.findAll();
    }
}
