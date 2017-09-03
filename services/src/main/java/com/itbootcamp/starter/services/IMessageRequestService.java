package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.MessageRequestEntity;
import com.itbootcamp.starter.datamodel.PersonEntity;

/**
 * Created by admin on 9/2/2017.
 */
public interface IMessageRequestService {
    Boolean save(Integer receiverId, String title, String text, PersonEntity personEntity);
}
