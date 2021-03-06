package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ProjectEntity;
import com.itbootcamp.starter.datamodel.RoleType;
import com.itbootcamp.starter.datamodel.VacancyEntity;
import com.itbootcamp.starter.services.IProjectService;
import com.itbootcamp.starter.services.IVacancyService;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.VacancyDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
@RestController
public class VacancyController {

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private IVacancyService vacancyService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private PersonService personService;

    @Autowired
    private EntityFactory entityFactory;

    private static final Logger logger = Logger.getLogger(Logger.class);

    @PreAuthorize("hasAnyAuthority('Admin','Customer','Mentor')")
    @RequestMapping(value = "api/project/{projectId}/vacancy", method = RequestMethod.PUT)
    ResponseEntity updateVacancy(@PathVariable Integer projectId, @RequestBody @Valid VacancyDTO vacancyDTO,
                                 BindingResult bindingResult,OAuth2Authentication oAuth2Authentication){

        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());


        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity==null) {

            logger.error("Project with id="+projectId+" does not exist.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if ((projectEntity.getCustomer().getId().equals(personEntity.getId()))
                ||(projectService.isMember(personEntity,projectEntity))
                ||(personEntity.getRole().getName().equals(RoleType.ROLE_ADMIN))) {

            if (vacancyService.update(entityFactory.getVacancyEntity(vacancyDTO),projectEntity)) {

                return new ResponseEntity<>(HttpStatus.OK);

            }


        }


        return new ResponseEntity<>(HttpStatus.FORBIDDEN);


    }

    @PreAuthorize("hasAnyAuthority('Admin','Customer','Mentor')")
    @RequestMapping(value = "api/project/{projectId}/vacancy", method = RequestMethod.POST)
    ResponseEntity addVacancy(@PathVariable Integer projectId, @RequestBody @Valid VacancyDTO vacancyDTO,BindingResult bindingResult,
                              OAuth2Authentication oAuth2Authentication){




        if (bindingResult.hasErrors()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }


        PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity==null) {

            logger.error("Project with id="+projectId+" does not exist.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if ((projectEntity.getCustomer().getId().equals(personEntity.getId()))
                ||(projectService.isMember(personEntity,projectEntity))
                ||(personEntity.getRole().getName().equals(RoleType.ROLE_ADMIN))) {

            if (vacancyService.add(entityFactory.getVacancyEntity(vacancyDTO),projectEntity)) {

                return new ResponseEntity<>(HttpStatus.CREATED);

            }


        }


        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasAnyAuthority('Admin','Customer','Mentor')")
    @RequestMapping(value = "api/project/{projectId}/vacancy/{vacancyId}", method = RequestMethod.DELETE)
    ResponseEntity deleteVacancy(@PathVariable Integer projectId,@PathVariable Integer vacancyId,
                                 OAuth2Authentication oAuth2Authentication){


        PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        ProjectEntity projectEntity=projectService.getById(projectId);

        VacancyEntity vacancyEntity=vacancyService.getById(vacancyId);

        if (projectEntity==null) {

            logger.error("Project with id="+projectId+" does not exist.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if (vacancyEntity==null) {

            logger.error("Vacancy with id="+projectId+" does not exist.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        if ((projectEntity.getCustomer().getId().equals(personEntity.getId()))
                ||(projectService.isMember(personEntity,projectEntity))
                ||(personEntity.getRole().getName().equals(RoleType.ROLE_ADMIN))) {

            if (vacancyService.delete(vacancyEntity,projectEntity)) {

                return new ResponseEntity<>(HttpStatus.OK);

            }


        }


        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "api/project/{projectId}/vacancies", method = RequestMethod.GET)
    ResponseEntity<List<VacancyDTO>> getVacancyByProject(@PathVariable Integer projectId){

        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity == null) {

            logger.error("Project with id="+projectId+" does not exist.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<VacancyDTO> vacancies=new ArrayList<>();

        for (VacancyEntity vacancyEntity:projectEntity.getVacancies()) {
            vacancies.add(dtoFactory.getVacancyDTO(vacancyEntity));
        }
        return new ResponseEntity<>(vacancies,HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "api/vacancy/{vacancyId}", method = RequestMethod.GET)
    ResponseEntity<VacancyDTO> getVacancy(@PathVariable Integer vacancyId){

        VacancyEntity vacancyEntity=vacancyService.getById(vacancyId);

        if (vacancyEntity == null) {

            logger.error("Vacancy with id="+vacancyId+" does not exist.");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dtoFactory.getVacancyDTO(vacancyEntity),HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/vacancy/search", method = RequestMethod.GET)
    ResponseEntity<List<VacancyDTO>> searchUsers(
            @RequestParam(required = false) List<String> positions,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) List<String> skills,
            @RequestParam(required = false) List<String> languages){

        List<VacancyEntity> vacancyEntityList =
                vacancyService.searchVacancies(positions, role, skills, languages);
        List<VacancyDTO> vacancyDTOList = new ArrayList<>();

        for (VacancyEntity vacancyEntity:vacancyEntityList) {
            vacancyDTOList.add(dtoFactory.getVacancyDTO(vacancyEntity));
        }
        return new ResponseEntity<>(vacancyDTOList, HttpStatus.OK);
    }



}
