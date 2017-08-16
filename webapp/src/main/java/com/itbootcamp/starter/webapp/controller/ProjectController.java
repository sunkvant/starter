package com.itbootcamp.starter.webapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 8/16/2017.
 */

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/api/project/{projectId}", method = RequestMethod.GET)
    ResponseEntity<String> getProject(@PathVariable Integer projectId) {

        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        JsonObject json = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        json.addProperty("name", projectEntity.getName());
        json.addProperty("description", projectEntity.getDescription());
        json.addProperty("dateStart", projectEntity.getDateStart().toString());
        json.addProperty("dateEnd",projectEntity.getDateEnd().toString());
        json.addProperty("contactInfo",projectEntity.getContactInfo());
        json.addProperty("customerId",projectEntity.getCustomer().getId());
        json.addProperty("projectStatus",projectEntity.getProjectStatus().getStatus());
        json.addProperty("projectCategory",projectEntity.getProjectCategory().getCategory());
        json.add("vacancies",gson.toJsonTree(projectEntity.getVacancies()));
        json.add("team",gson.toJsonTree(personService.getAllPersonsByProjectId(projectId)));





        return new ResponseEntity<>(json.toString(), HttpStatus.OK);

    }




}
