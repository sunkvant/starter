package com.itbootcamp.starter.services.impl;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.datamodel.RoleEntity;
import com.itbootcamp.starter.repository.RoleRepository;
import com.itbootcamp.starter.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 7/26/2017.
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getAllTypeMembers() {
        //return roleRepository.findByAdminRole(false);
        //CustomerEntity mentorEntity = new CustomerEntity();
        //profileRepository.save(mentorEntity);
        return null;
    }
}
