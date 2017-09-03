package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.MessageRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestType;
import com.itbootcamp.starter.repository.MessageRequestRepository;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.RequestTypeRepository;
import com.itbootcamp.starter.services.IMessageRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by admin on 9/2/2017.
 */
@Service
public class MessageRequestService implements IMessageRequestService {

    @Autowired
    private MessageRequestRepository messageRequestRepository;

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Boolean save(Integer receiverId, String title, String text, PersonEntity personEntity){

        if (receiverId == null || personRepository.findOne(receiverId) == null){
            return false;
        }

        if (personRepository.findOne(receiverId).getId().equals(personEntity.getId())){
            return false;
        }

        MessageRequestEntity messageRequestEntity = new MessageRequestEntity();

        messageRequestEntity.setTitle(title);
        messageRequestEntity.setText(text);
        messageRequestEntity.setRequestType(requestTypeRepository.findByType(RequestType.REQUEST_MESSAGE));
        messageRequestEntity.setRead(false);
        messageRequestEntity.setDate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        messageRequestEntity.setSenderPerson(personEntity);
        messageRequestEntity.setSenderVisible(true);
        messageRequestEntity.setReceiverPerson(personRepository.findOne(receiverId));
        messageRequestEntity.setReceiverVisible(true);

        messageRequestRepository.save(messageRequestEntity);

        return true;
    }
}
