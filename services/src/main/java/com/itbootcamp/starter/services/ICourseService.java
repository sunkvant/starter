package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.CourseEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;

public interface ICourseService {

    CourseEntity getById(Integer courseId);
    Boolean add(CourseEntity courseEntity, PersonEntity personEntity);
    Boolean update(CourseEntity courseEntity,PersonEntity personEntity);
    Boolean delete(CourseEntity courseEntity,PersonEntity personEntity);


}
