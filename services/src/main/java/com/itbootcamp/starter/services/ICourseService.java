package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.impl.CourseEntity;
import com.itbootcamp.starter.datamodel.impl.PersonEntity;

public interface ICourseService {

    Boolean add(CourseEntity courseEntity, PersonEntity personEntity);


}
