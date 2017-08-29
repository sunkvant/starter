package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.services.impl.SkillService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "api/skills",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {

        return new ResponseEntity<>(dtoFactory.getSkills(skillService.getAll()),HttpStatus.OK);

    }

}
