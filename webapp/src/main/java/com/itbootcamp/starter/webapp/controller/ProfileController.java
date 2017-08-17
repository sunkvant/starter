package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.google.gson.*;
import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import com.itbootcamp.starter.datamodel.impl.RoleType;
import com.itbootcamp.starter.services.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 8/14/2017.
 */
@Controller
public class ProfileController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.GET)
    ResponseEntity<String> getProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String currentRole = personEntity.getRole().getName();

        JsonObject json = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        json.addProperty("login", personEntity.getLogin());
        json.addProperty("isBlocked", personEntity.getBlocked());
        json.addProperty("role", personEntity.getRole().getName());
        json.add("contact", gson.toJsonTree(personEntity.getContact()));

        if (currentRole.equals(RoleType.ROLE_MENTOR) || currentRole.equals(RoleType.ROLE_TRAINEE)) {
            json.addProperty("isApproved", personEntity.getProfile().getApproved());
            json.addProperty("direction", personEntity.getProfile().getDirection().getName());
            json.add("courses", gson.toJsonTree(personEntity.getProfile().getCourses()));
            //json.add("educations", gson.toJsonTree(personEntity.getProfile().getEducations()));

            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < personEntity.getProfile().getEducations().size(); i++) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", personEntity.getProfile().getEducations().get(i).getId());
                jsonObject.addProperty("name", personEntity.getProfile().getEducations().get(i).getName());
                jsonObject.addProperty("faculty", personEntity.getProfile().getEducations().get(i).getFaculty());
                jsonObject.addProperty("speciality", personEntity.getProfile().getEducations().get(i).getSpeciality());
                jsonObject.addProperty("graduationYear", personEntity.getProfile().getEducations().get(i).getGraduationYear());
                jsonObject.addProperty("educationType", personEntity.getProfile().getEducations().get(i).getEducationTypeEntity().getType());
                jsonArray.add(jsonObject);
            }
            json.add("educations", jsonArray);

            json.add("workplaces", gson.toJsonTree(personEntity.getProfile().getWorkplaces()));
            json.add("skills", gson.toJsonTree(personEntity.getProfile().getSkills()));

            if (currentRole.equals(RoleType.ROLE_MENTOR)) {
                json.addProperty("experience", personEntity.getMentorInfo().getExperience());
                json.addProperty("isMentorExp", personEntity.getMentorInfo().getMentorExp());
            }

            //TODO if (token.id != personId) json.add(projects);

        } else if (currentRole.equals(RoleType.ROLE_CUSTOMER)) {
            json.add("projects", gson.toJsonTree(personEntity.getCustomerProjects()));
        }

        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }

}