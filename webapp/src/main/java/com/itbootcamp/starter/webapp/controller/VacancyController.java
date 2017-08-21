package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import com.itbootcamp.starter.datamodel.impl.VacancyEntity;
import com.itbootcamp.starter.services.IProjectService;
import com.itbootcamp.starter.services.IVacancyService;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.VacancyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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



    @RequestMapping(value = "api/project/{projectId}/vacancies", method = RequestMethod.GET)
    ResponseEntity<List<VacancyDTO>> getVacancyByProject(@PathVariable Integer projectId){

        ProjectEntity projectEntity=projectService.getById(projectId);

        if (projectEntity == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        List<VacancyDTO> vacancies=new ArrayList<>();

        for (int i = 0; i < projectEntity.getVacancies().size(); i++) {

            vacancies.add(dtoFactory.getVacancyDTO(projectEntity.getVacancies().get(i)));

        }

        return new ResponseEntity<>(vacancies,HttpStatus.OK);


    }


    @RequestMapping(value = "api/vacancy/{vacancyId}", method = RequestMethod.GET)
    ResponseEntity<VacancyDTO> getVacancy(@PathVariable Integer vacancyId){

        VacancyEntity vacancyEntity=vacancyService.getById(vacancyId);

        if (vacancyEntity == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity<>(dtoFactory.getVacancyDTO(vacancyEntity),HttpStatus.OK);
    }



}
