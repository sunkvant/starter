package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.CityEntity;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        List<CityEntity> citiesEntity=new ArrayList<>();

        if (countryName==null) {

            citiesEntity=cityService.getAll();

        } else {

            if (countryService.getByName(countryName)!=null) {

                citiesEntity=cityService.getAllByCountryId(countryService.getByName(countryName).getId());

            } else {

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }

        }


        Set<String> set=new HashSet<>();

        for(CityEntity city:citiesEntity) {

            set.add(city.getName());

        }

        return new ResponseEntity(set,HttpStatus.OK);

    }


}
