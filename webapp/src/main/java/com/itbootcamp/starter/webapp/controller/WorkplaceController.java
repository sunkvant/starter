package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.WorkplaceEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.WorkplaceService;
import com.itbootcamp.starter.webapp.dto.CourseDTO;
import com.itbootcamp.starter.webapp.dto.EducationDTO;
import com.itbootcamp.starter.webapp.dto.WorkplaceDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkplaceController {

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private PersonService personService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private DTOFactory dtoFactory;



    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/workplaces", method = RequestMethod.GET)
    ResponseEntity getAllWorkplaces() {

        List<WorkplaceDTO> workplacesDTO=new ArrayList<>();
        List<WorkplaceEntity> workplacesEntity=workplaceService.getAll();

        for(int i=0; i<workplacesEntity.size(); i++) {

            workplacesDTO.add(dtoFactory.getWorkplaceDTO(workplacesEntity.get(i)));

        }

        return new ResponseEntity<>(workplacesDTO,HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('Admin','Moder')")
    @RequestMapping(value = "api/profile/{personId}/workplace",method = RequestMethod.POST)
    ResponseEntity addWorkplaceCustomProfile(@RequestBody @Valid WorkplaceDTO workplaceDTO, @PathVariable Integer personId, BindingResult bindingResult) {

        return addWorkplace(workplaceDTO,personId,bindingResult,null);

    }

    @PreAuthorize("hasAnyAuthority('Admin','Moder')")
    @RequestMapping(value = "api/profile/{personId}/workplace",method = RequestMethod.PUT)
    ResponseEntity updateWorkplaceCustomProfile(@RequestBody @Valid WorkplaceDTO workplaceDTO, @PathVariable Integer personId, BindingResult bindingResult) {

        return updateWorkplace(workplaceDTO,personId,bindingResult,null);

    }


    @PreAuthorize("hasAnyAuthority('Admin','Moder')")
    @RequestMapping(value = "api/profile/{personId}/workplace/{workplaceId}",method = RequestMethod.DELETE)
    ResponseEntity deleteWorkplaceCustomProfile(@RequestBody @Valid WorkplaceDTO workplaceDTO, @PathVariable Integer personId, @PathVariable Integer workplaceId) {

        return deleteWorkplace(workplaceId,personId,null);

    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/workplace", method = RequestMethod.POST)
    ResponseEntity addWorkplace(@RequestBody @Valid WorkplaceDTO workplaceDTO, Integer personId, BindingResult bindingResult,
                                OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }


        if (!workplaceService.add(entityFactory.getWorkplaceEntity(workplaceDTO),personEntity)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/profile/workplace", method = RequestMethod.PUT)
    ResponseEntity updateWorkplace(@RequestBody @Valid WorkplaceDTO workplaceDTO, Integer personId, BindingResult bindingResult,
                                   OAuth2Authentication oAuth2Authentication) {


        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }


        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        if (!workplaceService.update(entityFactory.getWorkplaceEntity(workplaceDTO),personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<> (HttpStatus.OK);

    }


    @PreAuthorize("hasAnyAuthority('Mentor','Trainee')")
    @RequestMapping(value = "/api/profile/workplace/{workplaceId}", method = RequestMethod.DELETE)
    ResponseEntity deleteWorkplace(@PathVariable Integer workplaceId, Integer personId,
                                   OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;
        WorkplaceEntity workplaceEntity;

        if(oAuth2Authentication==null) {

            personEntity = personService.getById(personId);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }

        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        workplaceEntity=workplaceService.getById(workplaceId);

        if (workplaceEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!workplaceService.delete(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        return new ResponseEntity<CourseDTO>(HttpStatus.OK);

    }


}
