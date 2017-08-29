package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.RoleEntity;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by admin on 7/26/2017.
 */
public interface IRoleService {

    RoleEntity getByName(String name);

}
