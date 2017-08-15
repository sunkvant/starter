package com.itbootcamp.starter.datamodel;

import com.itbootcamp.starter.datamodel.impl.ProjectEntity;
import com.itbootcamp.starter.datamodel.impl.ReviewEntity;

import java.util.List;

/**
 * Created by admin on 8/9/2017.
 */
public interface ICustomerEntity extends IPersonEntity{
    List<ReviewEntity> getSenderReviews();
    void setSenderReviews(List<ReviewEntity> senderReviews);

    List<ProjectEntity> getCustomerProjects();
    void setCustomerProjects(List<ProjectEntity> customerProjects);
}
