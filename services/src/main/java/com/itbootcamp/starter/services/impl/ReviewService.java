package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.ReviewEntity;
import com.itbootcamp.starter.repository.ReviewRepository;
import com.itbootcamp.starter.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 8/21/2017.
 */
@Service
public class ReviewService implements IReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void save(ReviewEntity reviewEntity) {
        reviewRepository.save(reviewEntity);
    }
}
