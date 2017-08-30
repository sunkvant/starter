package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.services.IDirectionService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectionController {

    @Autowired
    IDirectionService directionService;

    @Autowired
    DTOFactory dtoFactory;

    @RequestMapping(value = "api/directions", method = RequestMethod.GET)
    public ResponseEntity getAll() {


        return new ResponseEntity(dtoFactory.getDirections(directionService.getAll()), HttpStatus.OK);


    }


}
