package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.ConsultationRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestType;
import com.itbootcamp.starter.datamodel.VacancyRequestEntity;
import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IConsultationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by admin on 9/3/2017.
 */
@Service
public class ConsultationRequestService implements IConsultationRequestService {

    @Autowired
    private ConsultationRequestRepository consultationRequestRepository;

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Boolean save(Integer projectId, String title, Boolean isSingle, Integer receiverPersonId, PersonEntity personEntity) {

        if (projectId == null || !projectRepository.exists(projectId)) {
            return false;
        }
        if (receiverPersonId == null || !personRepository.exists(receiverPersonId)) {
            return false;
        }
        if (receiverPersonId.equals(personEntity.getId())) {
            return false;
        }

        ConsultationRequestEntity consultationRequestEntity = new ConsultationRequestEntity();

        consultationRequestEntity.setTitle(title);
        consultationRequestEntity.setProject(projectRepository.findOne(projectId));
        consultationRequestEntity.setSingle(isSingle);
        consultationRequestEntity.setDate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        consultationRequestEntity.setRead(false);
        consultationRequestEntity.setSenderPerson(personEntity);
        consultationRequestEntity.setReceiverPerson(personRepository.findOne(receiverPersonId));
        consultationRequestEntity.setSenderVisible(true);
        consultationRequestEntity.setReceiverVisible(true);
        consultationRequestEntity.setRequestType(requestTypeRepository.findByType(RequestType.REQUEST_CONSULTATION));

        consultationRequestRepository.save(consultationRequestEntity);

        return true;
    }
}
