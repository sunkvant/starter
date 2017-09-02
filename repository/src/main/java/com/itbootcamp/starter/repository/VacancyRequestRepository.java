package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.VacancyRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 9/2/2017.
 */
@Repository
public interface VacancyRequestRepository extends CrudRepository<VacancyRequestEntity, Integer>{
    List<VacancyRequestEntity> findAllByReceiverPersonAndReceiverVisible(PersonEntity personEntity, Boolean receiverVisible);

    List<VacancyRequestEntity> findAllBySenderPersonAndSenderVisible(PersonEntity personEntity, Boolean senderVisible);

    List<VacancyRequestEntity> findAllByReceiverPersonAndReceiverVisibleOrSenderPersonAndSenderVisible(
            PersonEntity receiverPerson, Boolean receiverVisible, PersonEntity senderPerson, Boolean senderVisible);
}
