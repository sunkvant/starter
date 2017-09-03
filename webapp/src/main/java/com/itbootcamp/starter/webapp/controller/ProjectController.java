package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ProjectEntity;
import com.itbootcamp.starter.datamodel.RoleType;
import com.itbootcamp.starter.datamodel.VacancyEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.ProjectService;
import com.itbootcamp.starter.services.impl.VacancyService;
import com.itbootcamp.starter.webapp.dto.MemberDTO;
import com.itbootcamp.starter.webapp.dto.ProjectDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
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
    private VacancyService vacancyService;

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private EntityFactory entityFactory;

    @PreAuthorize("hasAnyAuthority('Admin','Moder','Customer')")
    @RequestMapping(value = "/api/project/{projectId}/close",method = RequestMethod.POST)
    ResponseEntity close(@PathVariable Integer projectId, OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        if (projectEntity.getCustomer().getId()==personEntity.getId()) {

            if (!projectService.closeProject(projectEntity)) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }


        }


        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('Mentor','Customer')")
    @RequestMapping(value = "/api/project/team/profile/{personId}/vacancy/{vacancyId}/",method = RequestMethod.POST)
    ResponseEntity addMember(@PathVariable Integer personId, @PathVariable Integer vacancyId,
                             OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        PersonEntity member = personService.getById(personId);

        VacancyEntity vacancyEntity= vacancyService.getById(vacancyId);

        if (member==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (vacancyEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        ProjectEntity projectEntity=vacancyEntity.getProject();

        if ((projectEntity.getCustomer().getId().equals(personEntity.getId()))
                ||(projectService.isMember(personEntity,projectEntity))) {

            if (!projectService.addMember(vacancyEntity,member)) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }


        }


        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    @PreAuthorize("hasAuthority('Customer')")
    @RequestMapping(value = "/api/project/", method = RequestMethod.POST)
    ResponseEntity addProject(@RequestBody @Valid ProjectDTO projectDTO, BindingResult bindingResult,
                              OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        if (!projectService.create(entityFactory.getProjectEntity(projectDTO),personEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('Moder','Admin','Customer')")
    @RequestMapping(value = "/api/project/{projectId}", method = RequestMethod.PUT)
    ResponseEntity updateProject(@PathVariable Integer projectId,@RequestBody @Valid ProjectDTO projectDTO, BindingResult bindingResult,
                                 OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity==null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (!(personEntity.getId().equals(projectEntity.getCustomer().getId()))
                &&(!personEntity.getRole().getName().equals(RoleType.ROLE_ADMIN))
                &&(!personEntity.getRole().getName().equals(RoleType.ROLE_MODER))) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        if (!projectService.update(entityFactory.getProjectEntity(projectDTO),projectEntity)) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/project/{projectId}", method = RequestMethod.GET)
    ResponseEntity<ProjectDTO> getProject(@PathVariable Integer projectId) {

        if (!projectService.isExist(projectId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ProjectEntity projectEntity = projectService.getById(projectId);

        return new ResponseEntity<>(dtoFactory.getProjectDTO(projectEntity), HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("isAuthenticated()")
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