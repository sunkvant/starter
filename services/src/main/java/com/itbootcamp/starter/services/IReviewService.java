package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ReviewEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by admin on 8/21/2017.
 */
public interface IReviewService {
    Boolean add(ReviewEntity reviewEntity, PersonEntity senderPerson);
    Boolean delete(Integer reviewId);
    Boolean isExist(Integer reviewId);
}
