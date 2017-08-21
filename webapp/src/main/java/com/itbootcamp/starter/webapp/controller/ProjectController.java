package com.itbootcamp.starter.webapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import com.itbootcamp.starter.datamodel.impl.RoleType;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.ProjectService;
import com.itbootcamp.starter.webapp.dto.DTOFactory;
import com.itbootcamp.starter.webapp.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping(value = "/api/project/{projectId}", method = RequestMethod.GET)
    ResponseEntity<ProjectDTO> getProject(@PathVariable Integer projectId, OAuth2Authentication auth) {

        System.out.println(auth.getName());

        ProjectEntity projectEntity = projectService.getById(projectId);

        if (projectEntity == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ProjectDTO>(dtoFactory.getProjectDTO(projectEntity),HttpStatus.OK);
    }

    // TODO access only TRAINEE, MENTOR, CUSTOMER
    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    ResponseEntity<List<ProjectDTO>> getProjects() {

        //TODO id = Token.getId
        Integer id = 1;

        PersonEntity personEntity = personService.getById(id);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String currentRole = personEntity.getRole().getName();




        List<ProjectDTO> projects = new ArrayList<>();

        if (currentRole.equals(RoleType.ROLE_CUSTOMER)) {


            for (int i = 0; i < personEntity.getCustomerProjects().size(); i++) {


                projects.add(dtoFactory.getProjectDTO(personEntity.getCustomerProjects().get(i)));

            }

        }
        else {

            for (int i = 0; i < projectService.getAllProjectsByPersonId(id).size(); i++) {


                projects.add(dtoFactory.getProjectDTO(projectService.getAllProjectsByPersonId(id).get(i)));

            }

        }


        return new ResponseEntity<List<ProjectDTO>>(projects, HttpStatus.OK);
    }


}
