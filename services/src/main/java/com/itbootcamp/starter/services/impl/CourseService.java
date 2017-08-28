package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.CourseEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.repository.CourseRepository;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public CourseEntity getById(Integer courseId) {
        return courseRepository.findOne(courseId);
    }

    @Override
    public Boolean add(CourseEntity courseEntity, PersonEntity personEntity) {

        if (personEntity.getProfile() == null) {

            return false;

        }


        courseEntity.setId(null);
        courseEntity.setProfile(personEntity.getProfile());
        personEntity.getProfile().getCourses().add(courseEntity);

        if (personRepository.save(personEntity) != null) {

            return true;

        }

        return false;

    }

    @Override
    public Boolean update(CourseEntity courseEntity, PersonEntity personEntity) {


        if ((personEntity.getProfile() == null)||(courseEntity.getId()==null)) {

            return false;

        }


        for (int i = 0; i < personEntity.getProfile().getCourses().size(); i++) {

            if (courseEntity.getId() == personEntity.getProfile().getCourses().get(i).getId()) {


                personEntity.getProfile().getCourses().get(i).setName(courseEntity.getName());
                personEntity.getProfile().getCourses().get(i).setSpeciality(courseEntity.getSpeciality());
                personEntity.getProfile().getCourses().get(i).setOrganization(courseEntity.getOrganization());
                personEntity.getProfile().getCourses().get(i).setGraduationYear(courseEntity.getGraduationYear());

                if (personRepository.save(personEntity) != null) {

                    return true;

                }
            }
        }


        return false;


    }


    @Override
    public Boolean delete(CourseEntity courseEntity, PersonEntity personEntity) {


        for (int i = 0; i < personEntity.getProfile().getCourses().size(); i++) {

            if (courseEntity.getId()==personEntity.getProfile().getCourses().get(i).getId()) {

                personEntity.getProfile().getCourses().remove(i);
                if (personRepository.save(personEntity)!=null) {

                    return true;

                }

            }

        }


        return false;

    }

}
