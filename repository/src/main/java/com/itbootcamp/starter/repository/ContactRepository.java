package com.itbootcamp.starter.repository;

import com.itbootcamp.starter.datamodel.impl.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<ContactEntity,Integer> {
}
