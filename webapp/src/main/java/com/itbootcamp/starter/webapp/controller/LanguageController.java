package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.LanguageEntity;
import com.itbootcamp.starter.services.impl.LanguageService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private DTOFactory dtoFactory;


    @RequestMapping(value = "api/languages",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {

        return new ResponseEntity<>(dtoFactory.getLanguages(languageService.getAll()), HttpStatus.OK);

    }


}
