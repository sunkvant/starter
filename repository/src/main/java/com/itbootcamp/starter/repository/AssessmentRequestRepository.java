package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.AssessmentRequestEntity;
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
public interface AssessmentRequestRepository extends CrudRepository<AssessmentRequestEntity, Integer>{
    List<AssessmentRequestEntity> findAllByReceiverPersonAndReceiverVisible(PersonEntity personEntity, Boolean receiverVisible);

    List<AssessmentRequestEntity> findAllBySenderPersonAndSenderVisible(PersonEntity personEntity, Boolean senderVisible);

    List<AssessmentRequestEntity> findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(
            PersonEntity receiverPerson, Boolean receiverVisible, PersonEntity senderPerson, Boolean senderVisible);
}
