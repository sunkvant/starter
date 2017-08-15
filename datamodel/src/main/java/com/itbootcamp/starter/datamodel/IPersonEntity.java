package com.itbootcamp.starter.datamodel;

import com.itbootcamp.starter.datamodel.impl.ContactEntity;
import com.itbootcamp.starter.datamodel.impl.RequestEntity;
import com.itbootcamp.starter.datamodel.impl.RoleEntity;

import java.util.List;

/**
 * Created by admin on 8/9/2017.
 */
public interface IPersonEntity {

    Integer getId();
    void setId(Integer id);

    String getLogin();
    void setLogin(String login);

    String getPassword();
    void setPassword(String password);

    RoleEntity getRole();
    void setRole(RoleEntity role);

    ContactEntity getContact();
    void setContact(ContactEntity contact);

    List<RequestEntity> getSenderRequests();
    void setSenderRequests(List<RequestEntity> senderRequests);

    List<RequestEntity> getReceiverRequests();
    void setReceiverRequests(List<RequestEntity> receiverRequests);

}
