package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.AssessmentRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestType;
import com.itbootcamp.starter.datamodel.VacancyRequestEntity;
import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IAssessmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by admin on 9/3/2017.
 */
@Service
public class AssessmentRequestService implements IAssessmentRequestService {

    @Autowired
    private AssessmentRequestRepository assessmentRequestRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Override
    public Boolean save(Integer receiverId, PersonEntity personEntity){

        AssessmentRequestEntity assessmentRequestEntity = new AssessmentRequestEntity();

        if (receiverId == null || !personRepository.exists(receiverId)){
            return false;
        }

        if (receiverId.equals(personEntity.getId())) {
            return false;
        }

        assessmentRequestEntity.setAnswered(false);
        assessmentRequestEntity.setDate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        assessmentRequestEntity.setRead(false);
        assessmentRequestEntity.setSenderPerson(personEntity);
        assessmentRequestEntity.setReceiverPerson(personRepository.findOne(receiverId));
        assessmentRequestEntity.setSenderVisible(true);
        assessmentRequestEntity.setReceiverVisible(true);
        assessmentRequestEntity.setRequestType(requestTypeRepository.findByType(RequestType.REQUEST_ASSESSMENT));

        assessmentRequestRepository.save(assessmentRequestEntity);

        return true;
    }


    @Override
    public Boolean answer(Integer assessmentRequestId, PersonEntity personEntity){


        if (assessmentRequestId == null || !assessmentRequestRepository.exists(assessmentRequestId)){
            return false;
        }

        if (!assessmentRequestRepository.findOne(assessmentRequestId).getReceiverPerson().getId().equals(personEntity.getId())){
            return false;
        }
        AssessmentRequestEntity assessmentRequestEntity = assessmentRequestRepository.findOne(assessmentRequestId);

        assessmentRequestEntity.setAnswered(true);

        assessmentRequestRepository.save(assessmentRequestEntity);
        return true;
    }



}
