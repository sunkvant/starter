package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.IConsultationRequestService;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IVacancyRequestService;
import com.itbootcamp.starter.webapp.dto.ConsultationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity sendConsultationRequest(@RequestBody ConsultationRequestDTO consultationRequestDTO,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!consultationRequestService.save(consultationRequestDTO.getProjectId(),
                                            consultationRequestDTO.getTitle(),
                                            consultationRequestDTO.getSingle(),
                                            consultationRequestDTO.getReceiverPersonId(),
                                            personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
