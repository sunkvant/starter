package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.MessageRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
@Repository
public interface MessageRequestRepository extends CrudRepository<MessageRequestEntity, Integer> {
    List<MessageRequestEntity> findAllByReceiverPersonAndReceiverVisible(PersonEntity personEntity, Boolean receiverVisible);

    List<MessageRequestEntity> findAllBySenderPersonAndSenderVisible(PersonEntity personEntity, Boolean senderVisible);

    List<MessageRequestEntity> findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(
            PersonEntity receiverPerson, Boolean receiverVisible, PersonEntity senderPerson, Boolean senderVisible);
}
