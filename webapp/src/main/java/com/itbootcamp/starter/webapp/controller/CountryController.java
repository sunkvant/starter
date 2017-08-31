package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.CountryEntity;
import com.itbootcamp.starter.services.impl.CountryService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @Autowired
    DTOFactory dtoFactory;

    @RequestMapping(value = "api/countries",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {


        List<CountryEntity> countriesEntity=countryService.getAll();


        Set<String> set=new HashSet<>();

        for(int i=0; i<countriesEntity.size(); i++) {

            set.add(countriesEntity.get(i).getName());

        }

        return new ResponseEntity(set,HttpStatus.OK);

    }


}
