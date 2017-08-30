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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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



        List<CourseDTO> coursesDTO=new ArrayList<>();
        List<CourseEntity> coursesEntity=courseService.getAll();

        for(int i=0; i<coursesEntity.size(); i++) {

            coursesDTO.add(dtoFactory.getCourseDTO(coursesEntity.get(i)));

        }

        return new ResponseEntity<>(coursesDTO,HttpStatus.OK);

    }


    @RequestMapping(value = "/api/profile/course", method = RequestMethod.POST)
    ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        PersonEntity personEntity = personService.getById(83); //TODO

        CourseEntity courseEntity=entityFactory.getCourseEntity(courseDTO);


        if (!courseService.add(courseEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<CourseDTO>(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/profile/course", method = RequestMethod.PUT)
    ResponseEntity updateCourse(@RequestBody @Valid CourseDTO courseDTO) {




        PersonEntity personEntity = personService.getById(54);  //TODO

        CourseEntity courseEntity=entityFactory.getCourseEntity(courseDTO);


        if (!courseService.update(courseEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<> (HttpStatus.OK);

    }

    @RequestMapping(value = "/api/profile/course/{courseId}", method = RequestMethod.DELETE)
    ResponseEntity deleteCourse(@PathVariable Integer courseId) {

        PersonEntity personEntity = personService.getById(54);  //TODO

        CourseEntity courseEntity=courseService.getById(courseId);

        if (courseEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!courseService.delete(courseEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<CourseDTO>(HttpStatus.OK);

    }

}
