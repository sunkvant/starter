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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "/api/profile/skills", method = RequestMethod.POST)
    ResponseEntity<ProfileDTO> addSkillsToProject(@RequestBody List<String> skills) {

        PersonEntity personEntity = personService.getById(85);  //TODO



        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        skillService.add(entityFactory.getSkillsEntity(skills),personEntity);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
