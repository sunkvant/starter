package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.SkillService;
import com.itbootcamp.starter.webapp.dto.ProfileDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private SkillService skillService;

    @Autowired
    private PersonService personService;

    @Autowired
    private EntityFactory entityFactory;

    @RequestMapping(value = "api/skills",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {

        return new ResponseEntity<>(dtoFactory.getSkills(skillService.getAll()),HttpStatus.OK);

    }


    @PreAuthorize("hasAnyAuthority('Admin')")
    @RequestMapping(value = "api/profile/{personId}/skills",method = RequestMethod.POST)
    ResponseEntity addEducationCustomProfile(@RequestBody List<String> skills, @PathVariable Integer personId) {

        return addSkillsToProfile(skills,personId,null);

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile/skills", method = RequestMethod.POST)
    ResponseEntity<ProfileDTO> addSkillsToProfile(@RequestBody List<String> skills, Integer personId,
                                                  OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }

        if(!skillService.add(entityFactory.getSkillsEntity(skills),personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
