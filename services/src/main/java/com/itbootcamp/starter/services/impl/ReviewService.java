package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ProjectStatus;
import com.itbootcamp.starter.datamodel.ReviewEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.ReviewRepository;
import com.itbootcamp.starter.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by admin on 8/21/2017.
 */
@Service
public class ReviewService implements IReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Boolean add(ReviewEntity reviewEntity,PersonEntity senderPerson) {

        if (reviewEntity.getProject()==null) {

            return false;

        }

        if (reviewEntity.getReceiverPerson()==null) {

            return false;

        }

        if (!reviewEntity.getProject().getProjectStatus().getStatus().equals(ProjectStatus.CLOSE)) {

            return false;

        }


        if (!projectService.isMember(reviewEntity.getReceiverPerson(),reviewEntity.getProject())) {

            return false;

        }

        if (!projectService.isMember(senderPerson,reviewEntity.getProject())) {

            return false;

        }


        reviewEntity.setDate(new Timestamp(System.currentTimeMillis()));
        reviewEntity.setSenderPerson(senderPerson);

        reviewRepository.save(reviewEntity);


        return true;


    }

    @Override
    public Boolean delete(Integer reviewId) {

        reviewRepository.delete(reviewId);

        return true;
    }

    @Override
    public Boolean isExist(Integer reviewId) {
        return reviewRepository.exists(reviewId);
    }
}
