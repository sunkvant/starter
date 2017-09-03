package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.*;
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

    @Override
    public RequestEntity getByRequestIdAndPerson(Integer requestId, PersonEntity personEntity) {
        return requestRepository.getByIdAndReceiverPersonAndReceiverVisibleOrIdAndSenderPersonAndSenderVisible(
                requestId,
                personEntity,
                true,
                requestId,
                personEntity,
                true);
    }

    @Override
    public Boolean deleteByRequestIdAndPerson(Integer requestId, PersonEntity personEntity) {

        RequestEntity requestEntity = requestRepository.getByIdAndReceiverPersonAndReceiverVisibleOrIdAndSenderPersonAndSenderVisible(
                requestId,
                personEntity,
                true,
                requestId,
                personEntity,
                true);

        if (requestEntity == null){
            return false;
        }

        if (requestEntity.getReceiverPerson().getId().equals(personEntity.getId())){
            requestEntity.setReceiverVisible(false);
        }else {
            requestEntity.setSenderVisible(false);
        }

        switch (requestEntity.getRequestType().getType()){
            case RequestType.REQUEST_MESSAGE: messageRequestRepository.save((MessageRequestEntity) requestEntity); break;
            case RequestType.REQUEST_VACANCY: vacancyRequestRepository.save((VacancyRequestEntity) requestEntity); break;
            case RequestType.REQUEST_CONSULTATION: consultationRequestRepository.save((ConsultationRequestEntity) requestEntity); break;
            case RequestType.REQUEST_ASSESSMENT: assessmentRequestRepository.save((AssessmentRequestEntity) requestEntity); break;
        }
        return true;
    }

    @Override
    public Boolean readByRequestIdAndPerson(Integer requestId, PersonEntity personEntity) {

        RequestEntity requestEntity = requestRepository.getByIdAndReceiverPersonAndReceiverVisible(
                requestId,
                personEntity,
                true);

        if (requestEntity == null){
            return false;
        }

        requestEntity.setRead(true);

        switch (requestEntity.getRequestType().getType()){
            case RequestType.REQUEST_MESSAGE: messageRequestRepository.save((MessageRequestEntity) requestEntity); break;
            case RequestType.REQUEST_VACANCY: vacancyRequestRepository.save((VacancyRequestEntity) requestEntity); break;
            case RequestType.REQUEST_CONSULTATION: consultationRequestRepository.save((ConsultationRequestEntity) requestEntity); break;
            case RequestType.REQUEST_ASSESSMENT: assessmentRequestRepository.save((AssessmentRequestEntity) requestEntity); break;
        }
        return true;
    }


}
