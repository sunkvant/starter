package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.CourseEntity;
import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.repository.CourseRepository;
import com.itbootcamp.starter.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Boolean add(CourseEntity courseEntity, PersonEntity personEntity) {

        if (personEntity.getProfile() == null) {

            return false;

        } else {

            courseEntity.setId(null);
            courseEntity.setProfile(personEntity.getProfile());

            if (courseRepository.save(courseEntity) != null) {

                return true;

            }

            return false;
        }
    }

    @Override
    public Boolean update(CourseEntity courseEntity, PersonEntity personEntity) {
        if (personEntity.getProfile() == null) {

            return false;

        } else {

            List<CourseEntity> courseEntities = personEntity.getProfile().getCourses();

            for (int i = 0; i < courseEntities.size(); i++) {

                if (courseEntity.getId() == courseEntities.get(i).getId()) {

                    courseEntity.setProfile(personEntity.getProfile());
                    if (courseRepository.save(courseEntity) != null) {

                        return true;

                    }

                }

            }

            return false;

        }
    }
}
