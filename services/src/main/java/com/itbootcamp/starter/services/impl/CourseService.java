package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.CourseEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.repository.CourseRepository;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


        for(CourseEntity course:personEntity.getProfile().getCourses()) {

            if (courseEntity.getId() == course.getId()) {


                course.setName(courseEntity.getName());
                course.setSpeciality(courseEntity.getSpeciality());
                course.setOrganization(courseEntity.getOrganization());
                course.setGraduationYear(courseEntity.getGraduationYear());

                if (personRepository.save(personEntity) != null) {

                    return true;

                }
            }
        }


        return false;


    }


    @Override
    public Boolean delete(CourseEntity courseEntity, PersonEntity personEntity) {


        for(CourseEntity course:personEntity.getProfile().getCourses()) {

            if (courseEntity.getId()==course.getId()) {

                personEntity.getProfile().getCourses().remove(course);
                if (personRepository.save(personEntity)!=null) {

                    return true;

                }

            }

        }


        return false;

    }

    @Override
    public List<CourseEntity> getAll() {
        return (List)courseRepository.findAll();
    }

}
