package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.webapp.dto.ProfileDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @RequestMapping(value = "/api/profile/search", method = RequestMethod.GET)
    ResponseEntity<List<ProfileDTO>> searchUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) Integer ageFrom,
            @RequestParam(required = false) Integer ageTo,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) List<String> directions,
            @RequestParam(required = false) List<String> skills,
            @RequestParam(required = false) String educationName,
            @RequestParam(required = false) Boolean isMentorExp){

        List<PersonEntity> personEntityList =
                personService.searchPersons(role, fullName, ageFrom, ageTo, country, city, directions, skills, educationName, isMentorExp);

        List<ProfileDTO> profileDTOList = new ArrayList<>();

        for (int i = 0; i < personEntityList.size(); i++) {
            profileDTOList.add(dtoFactory.getProfileDTO(personEntityList.get(i)));
        }

        return new ResponseEntity<>(profileDTOList, HttpStatus.OK);
    }

}