package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.ConsultationRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
@Repository
public interface ConsultationRequestRepository extends CrudRepository<ConsultationRequestEntity, Integer>{
    List<ConsultationRequestEntity> findAllByReceiverPersonAndReceiverVisible(PersonEntity personEntity, Boolean receiverVisible);

    List<ConsultationRequestEntity> findAllBySenderPersonAndSenderVisible(PersonEntity personEntity, Boolean senderVisible);

    List<ConsultationRequestEntity> findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(
            PersonEntity receiverPerson, Boolean receiverVisible, PersonEntity senderPerson, Boolean senderVisible);
}

