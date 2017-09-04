package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RoleType;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IVacancyRequestService;
import com.itbootcamp.starter.services.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 9/3/2017.
 */
@RestController
public class VacancyRequestController {

    @Autowired
    private IVacancyRequestService vacancyRequestService;

    @Autowired
    private IPersonService personService;

    @Autowired
    private IVacancyService vacancyService;

    @RequestMapping(value = "/api/message/vacancyRequest/{vacancyId}", method = RequestMethod.POST)
    public ResponseEntity sendVacancyRequest(@PathVariable Integer vacancyId,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity senderPerson = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        //send all members

        List<PersonEntity> personEntityList =
                personService.getAllPersonsByProjectId(vacancyService.getById(vacancyId).getProject().getId(), true);
        personEntityList.add(vacancyService.getById(vacancyId).getProject().getCustomer());

        for (PersonEntity receiverPerson : personEntityList) {
            if (receiverPerson.getRole().getName().equals(RoleType.ROLE_MENTOR) || receiverPerson.getRole().getName().equals(RoleType.ROLE_CUSTOMER)) {
                if (!vacancyRequestService.save(vacancyId, senderPerson, receiverPerson)) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
            }

        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/message/vacancyRequest/answer/{messageId}", method = RequestMethod.POST)
    public ResponseEntity answerVacancyRequest(@PathVariable(value = "messageId") Integer vacancyRequestId,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!vacancyRequestService.answer(vacancyRequestId, personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
