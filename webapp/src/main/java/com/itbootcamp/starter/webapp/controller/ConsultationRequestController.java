package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.IConsultationRequestService;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IVacancyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 9/3/2017.
 */
@RestController
public class ConsultationRequestController {

    @Autowired
    private IConsultationRequestService consultationRequestService;

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/api/message/consultationRequest", method = RequestMethod.POST)
    public ResponseEntity sendConsultationRequest(@RequestParam Integer projectId,
                                                  @RequestParam String title,
                                                  @RequestParam Boolean isSingle,
                                                  @RequestParam Integer receiverPersonId,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!consultationRequestService.save(projectId, title, isSingle, receiverPersonId, personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
