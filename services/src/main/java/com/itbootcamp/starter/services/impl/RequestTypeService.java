package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.RequestTypeEntity;
import com.itbootcamp.starter.repository.RequestTypeRepository;
import com.itbootcamp.starter.services.IRequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 9/3/2017.
 */
@Service
public class RequestTypeService implements IRequestTypeService {

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Override
    public RequestTypeEntity get(String type) {
        return requestTypeRepository.findByType(type);
    }
}
