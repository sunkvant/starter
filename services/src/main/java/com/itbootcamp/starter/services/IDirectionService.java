package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.DirectionEntity;

import java.util.List;

/**
 * Created by admin on 7/28/2017.
 */
public interface IDirectionService {
    DirectionEntity getByName(String name);
}
