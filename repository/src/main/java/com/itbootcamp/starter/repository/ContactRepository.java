package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<ContactEntity,Integer> {
}
