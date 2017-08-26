package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.ProjectCategoryEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.ProfileDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @RequestMapping(value = "/api/profile/", method = RequestMethod.PUT)
    ResponseEntity<ProfileDTO> updateProfile(@RequestBody @Valid ProfileDTO profileDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);

        }


        PersonEntity personEntity = entityFactory.getPersonEntity(profileDTO);

        if (!personService.update(personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.DELETE)
    ResponseEntity<ProfileDTO> deleteProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        personRepository.delete(personId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}