package com.itbootcamp.starter.datamodel;

import com.itbootcamp.starter.datamodel.impl.*;

import java.util.List;

/**
 * Created by admin on 8/9/2017.
 */
public interface IMemberEntity extends IPersonEntity {
    ProfileEntity getProfile();
    void setProfile(ProfileEntity profile);

    List<ReviewEntity> getReceiverReviews();
    void setReceiverReviews(List<ReviewEntity> receiverReviews);

    List<ReviewEntity> getSenderReviews();
    void setSenderReviews(List<ReviewEntity> senderReviews);

    List<TeamEntity> getTeams();
    void setTeams(List<TeamEntity> teams);

}
