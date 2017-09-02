package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestEntity;
import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
@Repository
public class RequestService implements IRequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private MessageRequestRepository messageRequestRepository;

    @Autowired
    private VacancyRequestRepository vacancyRequestRepository;

    @Autowired
    private ConsultationRequestRepository consultationRequestRepository;

    @Autowired
    private AssessmentRequestRepository assessmentRequestRepository;

    @Override
    public List<RequestEntity> getAll(PersonEntity personEntity){

        List<RequestEntity> requestEntityList = new ArrayList<>();
        requestEntityList.addAll(messageRequestRepository.findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(personEntity,true, personEntity, true));
        requestEntityList.addAll(vacancyRequestRepository.findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(personEntity,true, personEntity, true));
        requestEntityList.addAll(consultationRequestRepository.findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(personEntity,true, personEntity, true));
        requestEntityList.addAll(assessmentRequestRepository.findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(personEntity,true, personEntity, true));
        return requestEntityList;
    }
    @Override
    public List<RequestEntity> getReceivedRequests(PersonEntity personEntity){
        List<RequestEntity> requestEntityList = new ArrayList<>();
        requestEntityList.addAll(messageRequestRepository.findAllByReceiverPersonAndReceiverVisible(personEntity, true));
        requestEntityList.addAll(vacancyRequestRepository.findAllByReceiverPersonAndReceiverVisible(personEntity, true));
        requestEntityList.addAll(consultationRequestRepository.findAllByReceiverPersonAndReceiverVisible(personEntity, true));
        requestEntityList.addAll(assessmentRequestRepository.findAllByReceiverPersonAndReceiverVisible(personEntity, true));
        return requestEntityList;
    }
    @Override
    public List<RequestEntity> getSentRequests(PersonEntity personEntity){
        List<RequestEntity> requestEntityList = new ArrayList<>();
        requestEntityList.addAll(messageRequestRepository.findAllBySenderPersonAndSenderVisible(personEntity, true));
        requestEntityList.addAll(vacancyRequestRepository.findAllBySenderPersonAndSenderVisible(personEntity,true));
        requestEntityList.addAll(consultationRequestRepository.findAllBySenderPersonAndSenderVisible(personEntity,true));
        requestEntityList.addAll(assessmentRequestRepository.findAllBySenderPersonAndSenderVisible(personEntity,true));
        return requestEntityList;
    }

    @Override
    public Integer getCountNotRead(PersonEntity personEntity) {
        return requestRepository.countAllByReceiverPersonAndRead(personEntity, false);
    }
}
