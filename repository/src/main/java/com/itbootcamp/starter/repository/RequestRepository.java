package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 9/3/2017.
 */
@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, Integer>{
    Integer countAllByReceiverPersonAndRead(PersonEntity receiverPerson, Boolean isRead);
    RequestEntity getByIdAndReceiverPersonAndReceiverVisibleOrIdAndSenderPersonAndSenderVisible(Integer requestReceiverId,
                                                                                                     PersonEntity receiverPerson,
                                                                                                     Boolean receiverVisible,
                                                                                                     Integer requestSenderId,
                                                                                                     PersonEntity senderPerson,
                                                                                                     Boolean senderVisible);
    RequestEntity getByIdAndReceiverPersonAndReceiverVisible(Integer requestReceiverId,
                                                             PersonEntity receiverPerson,
                                                             Boolean receiverVisible);
}
