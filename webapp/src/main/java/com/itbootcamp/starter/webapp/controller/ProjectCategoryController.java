package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.ProjectCategoryEntity;
import com.itbootcamp.starter.services.impl.ProjectCategoryService;
import com.itbootcamp.starter.services.impl.ProjectStatusService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectCategoryController {

    @Autowired
    private ProjectCategoryService projectCategoryService;



    @RequestMapping(value = "api/categories",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAll() {

        List<ProjectCategoryEntity> projectCategoriesEntity=projectCategoryService.getAll();
        List<String> categories=new ArrayList<>();

        for(int i=0; i<projectCategoriesEntity.size(); i++) {

            categories.add(projectCategoriesEntity.get(i).getCategory());

        }

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

}
