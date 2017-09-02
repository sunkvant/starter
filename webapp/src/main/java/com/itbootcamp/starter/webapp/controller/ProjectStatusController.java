package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.ProjectStatusEntity;
import com.itbootcamp.starter.services.impl.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectStatusController {

    @Autowired
    ProjectStatusService projectStatusService;

    @RequestMapping(value = "api/statuses",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {


        List<ProjectStatusEntity> projectCategoriesEntity=projectStatusService.getAll();
        List<String> categories=new ArrayList<>();

        for(int i=0; i<projectCategoriesEntity.size(); i++) {

            categories.add(projectCategoriesEntity.get(i).getStatus());

        }

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

}
