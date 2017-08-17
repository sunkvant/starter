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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.management.relation.Role;
import java.util.List;

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

        ProjectEntity projectEntity = projectService.getById(projectId);

        if (projectEntity == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        JsonObject json = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();


        json.addProperty("customerId", projectEntity.getCustomer().getId());
        json.addProperty("name", projectEntity.getName());
        json.addProperty("description", projectEntity.getDescription());
        json.addProperty("dateStart", projectEntity.getDateStart().toString());
        json.addProperty("dateEnd", projectEntity.getDateEnd().toString());
        json.addProperty("contactInfo", projectEntity.getContactInfo());
        json.addProperty("projectStatus", projectEntity.getProjectStatus().getStatus());
        json.addProperty("projectCategory", projectEntity.getProjectCategory().getCategory());

        JsonArray languageArray = new JsonArray();
        for (int i = 0; i < projectEntity.getLanguages().size(); i++) {
            languageArray.add(projectEntity.getLanguages().get(i).getName());
        }

        json.add("languages", languageArray);

        JsonArray vacancyArray = new JsonArray();
        for (int i = 0; i < projectEntity.getVacancies().size(); i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", projectEntity.getVacancies().get(i).getId());
            jsonObject.addProperty("position", projectEntity.getVacancies().get(i).getPosition().getName());
            jsonObject.addProperty("personNumber", projectEntity.getVacancies().get(i).getPersonNumber());
            vacancyArray.add(jsonObject);
        }
        json.add("vacancies", vacancyArray);

        json.add("teamActive", getTeamJsonArray(projectId, true));
        json.add("teamNoActive", getTeamJsonArray(projectId, false));

        return new ResponseEntity<>(json.toString(), HttpStatus.OK);

    }

    private JsonArray getTeamJsonArray(Integer projectId, Boolean isMember) {
        JsonArray teamJsonArray = new JsonArray();
        List<PersonEntity> listActivePersonsOnProject = personService.getAllPersonsByProjectId(projectId, isMember);
        for (int i = 0; i < listActivePersonsOnProject.size(); i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", listActivePersonsOnProject.get(i).getId());
            jsonObject.addProperty("fullName", listActivePersonsOnProject.get(i).getContact().getFullName());
            jsonObject.addProperty("position", personService.getPositionByPersonIdAndByProjectId(personService.getAllPersonsByProjectId(projectId,isMember).get(i).getId(),projectId).getName());
            teamJsonArray.add(jsonObject);
        }
        return teamJsonArray;
    }

    // TODO access only TRAINEE, MENTOR, CUSTOMER
    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    ResponseEntity<String> getProjects() {

        //TODO id = Token.getId
        Integer id = 0;

        PersonEntity personEntity = personService.getById(id);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String currentRole = personEntity.getRole().getName();

        JsonObject json = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();


        if (currentRole.equals(RoleType.ROLE_CUSTOMER)) {
            json.add("projects", gson.toJsonTree(personEntity.getCustomerProjects()));
        } else {
            projectService.getAllProjectsByPersonId(id);
        }

        return null;
    }


}
