package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface ReviewEntityRepository extends CrudRepository<ReviewEntity,Long> {


}
