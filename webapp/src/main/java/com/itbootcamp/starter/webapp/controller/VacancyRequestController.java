package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IVacancyRequestService;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 9/3/2017.
 */
@RestController
public class VacancyRequestController {

    @Autowired
    private IVacancyRequestService vacancyRequestService;

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/api/message/vacancyRequest", method = RequestMethod.POST)
    public ResponseEntity sendVacancyRequest(@RequestParam Integer vacancyId,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!vacancyRequestService.save(vacancyId, personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/message/vacancyRequest/answer", method = RequestMethod.POST)
    public ResponseEntity answerVacancyRequest(@RequestParam(value = "messageId") Integer vacancyRequestId,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!vacancyRequestService.answer(vacancyRequestId, personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
