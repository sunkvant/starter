package com.itbootcamp.starter.repository;
import com.itbootcamp.starter.datamodel.impl.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by foooox on 20.7.17.
 */
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Long>{

    List<RoleEntity> findByName(String name);
}
