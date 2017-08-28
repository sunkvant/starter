package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by foooox on 21.7.17.
 */
@Repository
public interface CourseRepository extends CrudRepository<CourseEntity,Integer> {
    List<CourseEntity> findBySpecialityContains(String containsWord);
}
