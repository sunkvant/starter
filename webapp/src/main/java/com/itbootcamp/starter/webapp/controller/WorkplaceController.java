package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.WorkplaceEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.WorkplaceService;
import com.itbootcamp.starter.webapp.dto.CourseDTO;
import com.itbootcamp.starter.webapp.dto.WorkplaceDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @RequestMapping(value = "/api/workplaces", method = RequestMethod.GET)
    ResponseEntity getAllWorkplaces() {

        List<WorkplaceDTO> workplacesDTO=new ArrayList<>();
        List<WorkplaceEntity> workplacesEntity=workplaceService.getAll();

        for(int i=0; i<workplacesEntity.size(); i++) {

            workplacesDTO.add(dtoFactory.getWorkplaceDTO(workplacesEntity.get(i)));

        }

        return new ResponseEntity<>(workplacesDTO,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/profile/workplace", method = RequestMethod.POST)
    ResponseEntity addCourse(@RequestBody @Valid WorkplaceDTO workplaceDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        PersonEntity personEntity = personService.getById(83); //TODO

        WorkplaceEntity workplaceEntity=entityFactory.getWorkplaceEntity(workplaceDTO);


        if (!workplaceService.add(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<CourseDTO>(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/profile/workplace", method = RequestMethod.PUT)
    ResponseEntity updateWorkplace(@RequestBody @Valid WorkplaceDTO workplaceDTO) {




        PersonEntity personEntity = personService.getById(54);  //TODO

        WorkplaceEntity workplaceEntity=entityFactory.getWorkplaceEntity(workplaceDTO);


        if (!workplaceService.update(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<> (HttpStatus.OK);

    }


    @RequestMapping(value = "/api/profile/workplace/{workplaceId}", method = RequestMethod.DELETE)
    ResponseEntity deleteWorkplace(@PathVariable Integer workplaceId) {

        PersonEntity personEntity = personService.getById(54);  //TODO

        WorkplaceEntity workplaceEntity=workplaceService.getById(workplaceId);

        if (workplaceEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!workplaceService.delete(workplaceEntity,personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        return new ResponseEntity<CourseDTO>(HttpStatus.OK);

    }


}
