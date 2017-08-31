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
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.OAEPParameterSpec;
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



    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.GET)
    ResponseEntity getProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtoFactory.getProfileDTO(personEntity), HttpStatus.OK);
    }

    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile", method = RequestMethod.GET)
    ResponseEntity getProfileByLogin(@RequestParam(value = "login", required = false) String login,
                                     OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if (login!=null) {

            personEntity = personService.getByLogin(login);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }



        if (personEntity == null) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }


        return new ResponseEntity<>(dtoFactory.getProfileDTO(personEntity), HttpStatus.OK);
    }


    //@PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/checklogin/{login}", method = RequestMethod.GET)
    ResponseEntity getProfileByLogin(@RequestParam(value = "login", required = false) String login) {


        if (personService.getByLogin(login)!=null) {

            return new ResponseEntity<>(true,HttpStatus.OK);

        } else {


            return new ResponseEntity<>(false,HttpStatus.OK);
        }


    }



    @RequestMapping(value = "/api/profile/", method = RequestMethod.POST)
    ResponseEntity createProfile(@RequestBody @Valid ProfileDTO profileDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


            if (!personService.create(entityFactory.getPersonEntity(profileDTO))) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

        return new ResponseEntity (HttpStatus.CREATED);

    }

    @RequestMapping(value = "/api/profile/", method = RequestMethod.PUT)
    ResponseEntity<ProfileDTO> updateProfile(@RequestBody @Valid ContactDTO contactDTO, BindingResult bindingResult,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        personEntity.setContact(entityFactory.getContactEntity(contactDTO));

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