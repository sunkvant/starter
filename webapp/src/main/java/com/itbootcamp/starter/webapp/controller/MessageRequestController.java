package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.MessageRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.services.IMessageRequestService;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by admin on 9/3/2017.
 */
@RestController
public class MessageRequestController {

    @Autowired
    private IMessageRequestService messageRequestService;

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/api/message/messageRequest", method = RequestMethod.POST)
    public ResponseEntity sendMessageRequest(@RequestParam Integer receiverId,
                                             @RequestParam String title,
                                             @RequestParam String text,
                                             OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!messageRequestService.save(receiverId, title, text, personEntity)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
