package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestType;
import com.itbootcamp.starter.datamodel.TeamEntity;
import com.itbootcamp.starter.datamodel.VacancyRequestEntity;
import com.itbootcamp.starter.repository.RequestTypeRepository;
import com.itbootcamp.starter.repository.TeamRepository;
import com.itbootcamp.starter.repository.VacancyRepository;
import com.itbootcamp.starter.repository.VacancyRequestRepository;
import com.itbootcamp.starter.services.IVacancyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by Maxim Kazachenko on 9/3/2017.
 */
@Service
public class VacancyRequestService implements IVacancyRequestService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private VacancyRequestRepository vacancyRequestRepository;

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Override
    public Boolean save(Integer vacancyId, PersonEntity personEntity){

        VacancyRequestEntity vacancyRequestEntity = new VacancyRequestEntity();

        if (vacancyId == null || !vacancyRepository.exists(vacancyId)){
            return false;
        }

        vacancyRequestEntity.setAnswered(false);
        vacancyRequestEntity.setVacancy(vacancyRepository.findOne(vacancyId));
        vacancyRequestEntity.setDate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        vacancyRequestEntity.setRead(false);
        vacancyRequestEntity.setSenderPerson(personEntity);
        vacancyRequestEntity.setReceiverPerson(vacancyRepository.findOne(vacancyId).getProject().getCustomer());
        vacancyRequestEntity.setSenderVisible(true);
        vacancyRequestEntity.setReceiverVisible(true);
        vacancyRequestEntity.setRequestType(requestTypeRepository.findByType(RequestType.REQUEST_VACANCY));

        vacancyRequestRepository.save(vacancyRequestEntity);

        return true;
    }

    @Override
    public Boolean answer(Integer vacancyRequestId, PersonEntity personEntity){


        if (vacancyRequestId == null || !vacancyRequestRepository.exists(vacancyRequestId)){
            return false;
        }

        if (!vacancyRequestRepository.findOne(vacancyRequestId).getReceiverPerson().getId().equals(personEntity.getId())){
            return false;
        }
        VacancyRequestEntity vacancyRequestEntity = vacancyRequestRepository.findOne(vacancyRequestId);

        vacancyRequestEntity.setAnswered(true);

        vacancyRequestRepository.save(vacancyRequestEntity);
        return true;
    }

}
