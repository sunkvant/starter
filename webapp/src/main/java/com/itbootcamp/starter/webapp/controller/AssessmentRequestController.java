package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.IAssessmentRequestService;
import com.itbootcamp.starter.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 9/3/2017.
 */
@RestController
public class AssessmentRequestController {

    @Autowired
    private IAssessmentRequestService assessmentRequestService;

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/api/message/assessmentRequest/{receiverId}", method = RequestMethod.POST)
    public ResponseEntity sendAssessmentRequest(@PathVariable Integer receiverId,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!assessmentRequestService.save(receiverId, personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/message/assessmentRequest/answer/{messageId}", method = RequestMethod.POST)
    public ResponseEntity answerAssessmentRequest(@PathVariable(value = "messageId") Integer assessmentRequestId,
                                               OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!assessmentRequestService.answer(assessmentRequestId, personEntity)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
