package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.CourseEntity;
import com.itbootcamp.starter.datamodel.EducationEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.WorkplaceEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.impl.*;
import com.itbootcamp.starter.webapp.dto.*;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by admin on 8/14/2017.
 */
@RestController
public class ProfileController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private WorkplaceService workplaceService;


//education -----------------------------------------------------------------------
    @RequestMapping(value = "/api/profile/education", method = RequestMethod.POST)
    ResponseEntity addEducation(@RequestBody @Valid EducationDTO educationDTO,BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        EducationEntity educationEntity=entityFactory.getEducationEntity(educationDTO);

        PersonEntity personEntity = personService.getById(51); //TODO

        if (!educationService.add(educationEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/profile/education", method = RequestMethod.PUT)
    ResponseEntity updateEducation(@RequestBody @Valid EducationDTO educationDTO,BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        EducationEntity educationEntity=entityFactory.getEducationEntity(educationDTO);

        PersonEntity personEntity = personService.getById(51);   //TODO

        if (!educationService.update(educationEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/api/profile/education/{educationId}", method = RequestMethod.DELETE)
    ResponseEntity deleteEducation(@PathVariable Integer educationId) {

        PersonEntity personEntity = personService.getById(54);  //TODO

        EducationEntity educationEntity=educationService.getById(educationId);

        if (educationEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!educationService.delete(educationEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

            return new ResponseEntity<>(HttpStatus.OK);

    }

// COURSE------------------------------------------------------------------------------------------
    @RequestMapping(value = "/api/profile/course", method = RequestMethod.POST)
    ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO,BindingResult bindingResult) {

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

 //WORKPLACES


    @RequestMapping(value = "/api/profile/workplace", method = RequestMethod.POST)
    ResponseEntity addCourse(@RequestBody @Valid WorkplaceDTO workplaceDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        PersonEntity personEntity = personService.getById(83); //TODO

        WorkplaceEntity workplaceEntity=entityFactory.getWorkplaceEntity(workplaceDTO);


        if (!workplaceService.add(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<CourseDTO>(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/profile/workplace", method = RequestMethod.PUT)
    ResponseEntity updateWorkplace(@RequestBody @Valid WorkplaceDTO workplaceDTO) {




        PersonEntity personEntity = personService.getById(54);  //TODO

        WorkplaceEntity workplaceEntity=entityFactory.getWorkplaceEntity(workplaceDTO);


        if (!workplaceService.update(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<> (HttpStatus.OK);

    }


    @RequestMapping(value = "/api/profile/workplace/{workplaceId}", method = RequestMethod.DELETE)
    ResponseEntity deleteWorkplace(@PathVariable Integer workplaceId) {

        PersonEntity personEntity = personService.getById(54);  //TODO

        WorkplaceEntity workplaceEntity=workplaceService.getById(workplaceId);

        if (workplaceEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!workplaceService.delete(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        return new ResponseEntity<CourseDTO>(HttpStatus.OK);

    }

//PROFILE ----------------------------------------
    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.GET)
    ResponseEntity<ProfileDTO> getProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtoFactory.getProfileDTO(personEntity), HttpStatus.OK);
    }


    @RequestMapping(value = "/api/profile/", method = RequestMethod.POST)
    ResponseEntity<ProfileDTO> createProfile(@RequestBody @Valid ProfileDTO profileDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


            PersonEntity personEntity = entityFactory.getPersonEntity(profileDTO);

            if (!personService.create(personEntity)) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

/*    @RequestMapping(value = "/api/profile/", method = RequestMethod.PUT)
    ResponseEntity<ProfileDTO> updateProfile(@RequestBody @Valid ProfileDTO profileDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);

        }


        PersonEntity personEntity = entityFactory.getPersonEntity(profileDTO);

        if (!personService.update(personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }*/

    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.DELETE)
    ResponseEntity<ProfileDTO> deleteProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        personRepository.delete(personId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    //skills update,delete,add

    @RequestMapping(value = "/api/profile/skills", method = RequestMethod.POST)
    ResponseEntity<ProfileDTO> addSkills(@RequestBody List<SkillDTO> skillsDTO) {

        PersonEntity personEntity = personService.getById(85);  //TODO



        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        skillService.add(entityFactory.getSkillsEntity(skillsDTO),personEntity);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}