package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.impl.EducationService;
import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class UserController {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EducationService educationService;

    @Autowired
    private EducationTypeRepository educationTypeRepository;


    @RequestMapping(name = "/save", method = RequestMethod.POST)
    ResponseEntity addUser(@RequestBody EducationEntity educationEntity) {


        educationEntity.setEducationTypeEntity(educationTypeRepository.findOne(educationEntity.getEducationTypeId()));
        educationEntity.setProfile(profileRepository.findOne(1));


        educationService.update(educationEntity);


        return new ResponseEntity(HttpStatus.CREATED);


    }
}