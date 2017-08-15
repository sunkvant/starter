package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.services.IEducationService;
import com.itbootcamp.starter.datamodel.impl.EducationTypeEntity;
import com.itbootcamp.starter.datamodel.impl.ProfileEntity;
import com.itbootcamp.starter.datamodel.impl.EducationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 8/11/2017.
 */

@RestController
public class EducationController {

    @Autowired
    private IEducationService educationService;

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(value = {"api/profile/education"}, method = RequestMethod.POST)
    ResponseEntity save(RequestEntity<EducationEntity> education) {
        System.out.println("zashlo");


        EducationEntity educationEntity=new EducationEntity();

        educationEntity=education.getBody();





        EducationTypeEntity educationTypeEntity=new EducationTypeEntity();
        educationTypeEntity.setId(education.getBody().getEducationTypeId());



        ProfileEntity profileEntity=new ProfileEntity();
        profileEntity.setId(1);

        //profileRepository.findOne(id);




        educationEntity.setEducationTypeEntity(educationTypeEntity);

        educationService.addEducation(educationEntity);
        //educationService.addEducation(education.getBody());


        //educationEntity.set


        //converter.convertToEntity(educationDTO.getBody());


      //  EducationConverter educationConverter = new EducationConverter();



        //EducationEntity educationEntity = educationConverter.convertToEntity(educationDTO.getBody());

        //System.out.println(educationDTO.getBody().getName());

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = {"api/profile/education"}, method = RequestMethod.PUT)
    ResponseEntity update() {

        //TODO check userID
        Integer profile_id = 1;
        //EducationConverter converter = new EducationConverter();
       // EducationEntity educationEntity = converter.convertToEntity(educationDTO);

        //educationService.update(educationEntity);


        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"api/profile/education"}, method = RequestMethod.GET)
    ResponseEntity<List<EducationEntity>> getAll() {

        //TODO getID in Token
        Integer profile_id = 1;

        List<EducationEntity> educations = profileRepository.findOne(profile_id).getEducations();

        return new ResponseEntity<List<EducationEntity>>(educations, HttpStatus.OK);
    }


}
