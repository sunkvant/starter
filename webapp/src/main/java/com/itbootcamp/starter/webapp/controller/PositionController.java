package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PositionEntity;
import com.itbootcamp.starter.services.impl.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @RequestMapping(value = "api/positions",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {


        List<PositionEntity> positionsEntity=positionService.getAll();
        List<String> positions=new ArrayList<>();

        for(PositionEntity positionEntity:positionsEntity) {

            positions.add(positionEntity.getName());

        }

        return new ResponseEntity<>(positions, HttpStatus.OK);

    }

}
