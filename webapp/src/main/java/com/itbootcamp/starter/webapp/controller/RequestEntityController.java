package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.MessageRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestEntity;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IRequestService;
import com.itbootcamp.starter.webapp.dto.AbstractRequestDTO;
import com.itbootcamp.starter.webapp.dto.MessageRequestDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
@RestController
public class RequestEntityController {

    @Autowired
    IRequestService requestService;

    @Autowired
    IPersonService personService;

    @Autowired
    DTOFactory dtoFactory;

    @RequestMapping(value = "/api/messages/getReceived", method = RequestMethod.GET)
    public ResponseEntity<List<AbstractRequestDTO>> getReceivedMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        List<RequestEntity> requestEntityList = requestService.getReceivedRequests(personEntity);

        List<AbstractRequestDTO> abstractRequestDTOList = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntityList) {
            abstractRequestDTOList.add(dtoFactory.getRequestDTO(requestEntity));
        }

        return new ResponseEntity<>(abstractRequestDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getSent", method = RequestMethod.GET)
    public ResponseEntity<List<AbstractRequestDTO>> getSentMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        List<RequestEntity> requestEntityList = requestService.getSentRequests(personEntity);

        List<AbstractRequestDTO> abstractRequestDTOList = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntityList) {
            abstractRequestDTOList.add(dtoFactory.getRequestDTO(requestEntity));
        }

        return new ResponseEntity<>(abstractRequestDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<AbstractRequestDTO>> getAllMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        List<RequestEntity> requestEntityList = requestService.getAll(personEntity);

        List<AbstractRequestDTO> abstractRequestDTOList = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntityList) {
            abstractRequestDTOList.add(dtoFactory.getRequestDTO(requestEntity));
        }

        return new ResponseEntity<>(abstractRequestDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getCountNotRead", method = RequestMethod.GET)
    public ResponseEntity<Integer> getCountNotReadMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        Integer countNotRead = requestService.getCountNotRead(personEntity);

        return new ResponseEntity<>(countNotRead, HttpStatus.OK);
    }

}
