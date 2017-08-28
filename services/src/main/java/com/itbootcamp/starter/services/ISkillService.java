package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.SkillEntity;

import java.util.List;

/**
 * Created by admin on 7/28/2017.
 */
public interface ISkillService {
    Boolean add(List<SkillEntity> skillEntities, PersonEntity personEntity);
}
