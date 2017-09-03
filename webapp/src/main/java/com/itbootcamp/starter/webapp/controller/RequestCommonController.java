package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestEntity;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IRequestService;
import com.itbootcamp.starter.webapp.dto.AbstractRequestDTO;
import com.itbootcamp.starter.webapp.dto.RequestDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
@RestController
public class RequestCommonController {

    @Autowired
    IRequestService requestService;

    @Autowired
    IPersonService personService;

    @Autowired
    DTOFactory dtoFactory;


    @RequestMapping(value = "/api/message/{messageId}", method = RequestMethod.GET)
    public ResponseEntity<RequestDTO> getMessage(
            @PathVariable(value = "messageId", required = false) Integer requestId,
            OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        RequestEntity requestEntity = requestService.getByRequestIdAndPerson(requestId,personEntity);

        if (requestEntity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RequestDTO abstractRequestDTO = dtoFactory.getRequestDTO(requestEntity);

        return new ResponseEntity<>(abstractRequestDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/message/{messageId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMessage(
            @PathVariable(value = "messageId", required = false) Integer requestId,
            OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!requestService.deleteByRequestIdAndPerson(requestId,personEntity)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/message/setRead/{messageId}", method = RequestMethod.POST)
    public ResponseEntity readMessage(
            @PathVariable(value = "messageId", required = false) Integer requestId,
            OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!requestService.readByRequestIdAndPerson(requestId,personEntity)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getReceived", method = RequestMethod.GET)
    public ResponseEntity<List<RequestDTO>> getReceivedMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        List<RequestEntity> requestEntityList = requestService.getReceivedRequests(personEntity);

        List<RequestDTO> requestDTOList = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntityList) {
            requestDTOList.add(dtoFactory.getRequestDTO(requestEntity));
        }

        return new ResponseEntity<>(requestDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getSent", method = RequestMethod.GET)
    public ResponseEntity<List<RequestDTO>> getSentMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        List<RequestEntity> requestEntityList = requestService.getSentRequests(personEntity);

        List<RequestDTO> requestDTOList = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntityList) {
            requestDTOList.add(dtoFactory.getRequestDTO(requestEntity));
        }

        return new ResponseEntity<>(requestDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<RequestDTO>> getAllMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        List<RequestEntity> requestEntityList = requestService.getAll(personEntity);

        List<RequestDTO> requestDTOList = new ArrayList<>();

        for (RequestEntity requestEntity : requestEntityList) {
            requestDTOList.add(dtoFactory.getRequestDTO(requestEntity));
        }

        return new ResponseEntity<>(requestDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/messages/getCountNotRead", method = RequestMethod.GET)
    public ResponseEntity<Integer> getCountNotReadMessages(OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
        Integer countNotRead = requestService.getCountNotRead(personEntity);

        return new ResponseEntity<>(countNotRead, HttpStatus.OK);
    }

}
