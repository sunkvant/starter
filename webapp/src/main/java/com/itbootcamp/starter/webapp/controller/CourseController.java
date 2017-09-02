package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.CourseEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.impl.CourseService;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.CourseDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class CourseController {

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private PersonService personService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DTOFactory dtoFactory;


    @RequestMapping(value = "/api/courses", method = RequestMethod.GET)
    ResponseEntity getAllCourses() {

        List<CourseEntity> coursesEntity=courseService.getAll();

        Set<CourseDTO> set=new HashSet<>();

        for(int i=0; i<coursesEntity.size(); i++) {

            set.add(dtoFactory.getCourseDTO(coursesEntity.get(i)));

        }

        return new ResponseEntity(set,HttpStatus.OK);

    }


    @PreAuthorize("hasAnyAuthority('Admin','Moder')")
    @RequestMapping(value = "api/profile/{personId}/course",method = RequestMethod.POST)
    ResponseEntity addCourseCustomProfile(@RequestBody @Valid CourseDTO courseDTO, @PathVariable Integer personId, BindingResult bindingResult) {

        return addCourse(courseDTO,personId,bindingResult,null);

    }

    @PreAuthorize("hasAnyAuthority('Admin','Moder')")
    @RequestMapping(value = "api/profile/{personId}/course",method = RequestMethod.PUT)
    ResponseEntity updateCourseCustomProfile(@RequestBody @Valid CourseDTO courseDTO, @PathVariable Integer personId, BindingResult bindingResult) {

        return updateCourse(courseDTO,personId,bindingResult,null);

    }


    @PreAuthorize("hasAnyAuthority('Admin','Moder')")
    @RequestMapping(value = "api/profile/{personId}/course/{courseId}",method = RequestMethod.DELETE)
    ResponseEntity deleteCourseCustomProfile(@PathVariable Integer personId, @PathVariable Integer courseId) {

        return deleteCourse(courseId, personId,null);

    }




    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/course", method = RequestMethod.POST)
    ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO, Integer personId, BindingResult bindingResult,
                                        OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        if (!courseService.add(entityFactory.getCourseEntity(courseDTO),personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<CourseDTO>(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/course", method = RequestMethod.PUT)
    ResponseEntity updateCourse(@RequestBody @Valid CourseDTO courseDTO, Integer personId, BindingResult bindingResult,
                                OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if (!courseService.update(entityFactory.getCourseEntity(courseDTO),personEntity)) {

            return new ResponseEntity(HttpStatus.NOT_MODIFIED);

        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/course/{courseId}", method = RequestMethod.DELETE)
    ResponseEntity deleteCourse(@PathVariable Integer courseId,Integer personId,
                                OAuth2Authentication oAuth2Authentication) {


        PersonEntity personEntity;
        CourseEntity courseEntity;

        if(oAuth2Authentication == null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity == null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        courseEntity = courseService.getById(courseId);

        if (courseEntity == null) {

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }

        if (!courseService.delete(courseEntity,personEntity)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(HttpStatus.OK);

    }

}
