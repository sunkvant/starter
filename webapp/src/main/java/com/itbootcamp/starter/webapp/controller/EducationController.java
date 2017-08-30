package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.datamodel.EducationEntity;
import com.itbootcamp.starter.services.impl.EducationService;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.EducationDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by admin on 8/11/2017.
 */

@RestController
public class EducationController {

    @Autowired
    private EducationService educationService;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/api/profile/education", method = RequestMethod.POST)
    ResponseEntity addEducation(@RequestBody @Valid EducationDTO educationDTO, BindingResult bindingResult) {

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


}
