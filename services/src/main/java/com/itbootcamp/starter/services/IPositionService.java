package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PositionEntity;

public interface IPositionService {

    PositionEntity getByName(String name);

}
