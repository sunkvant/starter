package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.ProfileDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 8/14/2017.
 */
@RestController
public class ProfileController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.GET)
    ResponseEntity<ProfileDTO> getProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtoFactory.getProfileDTO(personEntity), HttpStatus.OK);
    }
}