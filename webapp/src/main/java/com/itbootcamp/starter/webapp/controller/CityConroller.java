package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.services.impl.CityService;
import com.itbootcamp.starter.services.impl.CountryService;
import com.itbootcamp.starter.services.impl.SkillService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityConroller {

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "api/cities",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll(@RequestParam(value = "country", required = false) String countryName) {

        if (countryName==null) {

            return new ResponseEntity<>(dtoFactory.getCities(cityService.getAll()), HttpStatus.OK);

        } else {


            return new ResponseEntity<>(dtoFactory.getCities(cityService.getAllByCountryId(countryService.getByName(countryName).getId())), HttpStatus.OK);

        }



    }


}
