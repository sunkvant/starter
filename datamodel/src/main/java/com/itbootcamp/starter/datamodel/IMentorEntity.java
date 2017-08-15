package com.itbootcamp.starter.datamodel;

import com.itbootcamp.starter.datamodel.impl.*;

/**
 * Created by admin on 8/9/2017.
 */
public interface IMentorEntity extends IMemberEntity {
    MentorInfoEntity getMentorInfo();
    void setMentorInfo(MentorInfoEntity mentorInfo);
}
