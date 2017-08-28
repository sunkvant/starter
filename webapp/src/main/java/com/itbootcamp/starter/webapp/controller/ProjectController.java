package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ProjectEntity;
import com.itbootcamp.starter.datamodel.RoleType;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.ProjectService;
import com.itbootcamp.starter.webapp.dto.ProjectDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    //@PreAuthorize("hasAuthority('Admin')")
    @RequestMapping(value = "/api/project/{projectId}", method = RequestMethod.GET)
    ResponseEntity<ProjectDTO> getProject(@PathVariable Integer projectId) {

        if (!projectService.isExist(projectId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ProjectEntity projectEntity = projectService.getById(projectId);

        return new ResponseEntity<>(dtoFactory.getProjectDTO(projectEntity), HttpStatus.OK);
    }


    @RequestMapping(value = "/api/profile/{personId}/projects", method = RequestMethod.GET)
    ResponseEntity<List<ProjectDTO>> getProjects(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String currentRole = personEntity.getRole().getName();

        List<ProjectDTO> projects = new ArrayList<>();

        if (currentRole.equals(RoleType.ROLE_CUSTOMER)) {
            for (int i = 0; i < personEntity.getCustomerProjects().size(); i++) {
                projects.add(dtoFactory.getProjectDTO(personEntity.getCustomerProjects().get(i)));
            }

        } else {
            for (int i = 0; i < projectService.getAllProjectsByPersonId(personId).size(); i++) {
                projects.add(dtoFactory.getProjectDTO(projectService.getAllProjectsByPersonId(personId).get(i)));
            }
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/project/search", method = RequestMethod.GET)
    ResponseEntity<List<ProjectDTO>> searchProject(
            @RequestParam(value = "name", required = false) String projectName,
            @RequestParam(value = "categories", required = false) List<String> projectCategoryList,
            @RequestParam(value = "statuses", required = false) List<String> projectStatusList,
            @RequestParam(value = "languages", required = false) List<String> projectLanguageList) {


        List<ProjectEntity> projectEntityList =
                projectService.searchProjects(projectName, projectCategoryList, projectStatusList, projectLanguageList);

        List<ProjectDTO> projectDTOList = new ArrayList<>();

        for (int i = 0; i < projectEntityList.size(); i++) {
            projectDTOList.add(dtoFactory.getProjectDTO(projectEntityList.get(i)));
        }
        return new ResponseEntity<>(projectDTOList, HttpStatus.OK);
    }
}