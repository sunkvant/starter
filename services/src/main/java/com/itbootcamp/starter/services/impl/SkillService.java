package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.RoleEntity;
import com.itbootcamp.starter.repository.RoleRepository;
import com.itbootcamp.starter.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 7/28/2017.
 */
public class SkillService implements ISkillService {
    @Autowired
    RoleRepository skillRepository;

    @Override
    public List<RoleEntity> getAll() {
        return (List<RoleEntity>) skillRepository.findAll();
    }
}
