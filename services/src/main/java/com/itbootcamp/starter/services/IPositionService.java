package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PositionEntity;

import java.util.List;

public interface IPositionService {

    PositionEntity getByName(String name);
    List<PositionEntity> getAll();


}
