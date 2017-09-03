package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.datamodel.EducationEntity;
import com.itbootcamp.starter.services.impl.EducationService;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.EducationDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.OAEPParameterSpec;
import javax.validation.Valid;
import java.util.*;

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
    private DTOFactory dtoFactory;

    @Autowired
    private PersonService personService;


    @RequestMapping(value = "/api/educations", method = RequestMethod.GET)
    ResponseEntity getAllEducations() {

        List<EducationEntity> educationsEntity=educationService.getAll();

        Set<EducationDTO> set=new HashSet<>();

        for(int i=0; i<educationsEntity.size(); i++) {

            set.add(dtoFactory.getEducationDTO(educationsEntity.get(i)));

        }

        return new ResponseEntity(set,HttpStatus.OK);
    }



    @PreAuthorize("hasAnyAuthority('Admin')")
    @RequestMapping(value = "api/profile/{personId}/education",method = RequestMethod.POST)
    ResponseEntity addEducationCustomProfile(@RequestBody @Valid EducationDTO educationDTO, @PathVariable Integer personId, BindingResult bindingResult) {

        return addEducation(educationDTO,personId,bindingResult,null);

    }


    @PreAuthorize("hasAnyAuthority('Admin')")
    @RequestMapping(value = "api/profile/{personId}/education",method = RequestMethod.PUT)
    ResponseEntity updateEducationCustomProfile(@RequestBody @Valid EducationDTO educationDTO, @PathVariable Integer personId, BindingResult bindingResult) {

        return updateEducation(educationDTO,personId,bindingResult,null);

    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @RequestMapping(value = "api/profile/{personId}/education/{educationId}",method = RequestMethod.DELETE)
    ResponseEntity deleteEducationCustomProfile(@PathVariable Integer personId, @PathVariable Integer educationId) {

        return deleteEducation(educationId, personId,null);

    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/education", method = RequestMethod.POST)
    ResponseEntity addEducation(@RequestBody @Valid EducationDTO educationDTO, Integer personId, BindingResult bindingResult,
                                OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!educationService.add(entityFactory.getEducationEntity(educationDTO),personEntity)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/education", method = RequestMethod.PUT)
    ResponseEntity updateEducation(@RequestBody @Valid EducationDTO educationDTO, Integer personId, BindingResult bindingResult,
                                   OAuth2Authentication oAuth2Authentication) {


        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if (!educationService.update(entityFactory.getEducationEntity(educationDTO),personEntity)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/education/{educationId}", method = RequestMethod.DELETE)
    ResponseEntity deleteEducation(@PathVariable Integer educationId, Integer personId,
                                   OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;
        EducationEntity educationEntity;

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        educationEntity=educationService.getById(educationId);

        if (educationEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if (!educationService.delete(educationEntity,personEntity)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(HttpStatus.OK);

    }


}
