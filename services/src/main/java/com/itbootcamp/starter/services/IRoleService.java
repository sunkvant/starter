package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.RoleEntity;

import java.util.List;

/**
 * Created by admin on 7/26/2017.
 */
public interface IRoleService {
    /*
    * Get roles: Customer, Mentor, Trainee
    */
    List<RoleEntity> getAllTypeMembers();

}
