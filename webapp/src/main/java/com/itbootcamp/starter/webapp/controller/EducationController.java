package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.datamodel.EducationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 8/11/2017.
 */

@RestController
public class EducationController {

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(value = {"api/profile/education"}, method = RequestMethod.GET)
    ResponseEntity<List<EducationEntity>> getAll() {

        //TODO getID in Token
        Integer profile_id = 1;

        List<EducationEntity> educations = profileRepository.findOne(profile_id).getEducations();

        return new ResponseEntity<>(educations, HttpStatus.OK);
    }


}
