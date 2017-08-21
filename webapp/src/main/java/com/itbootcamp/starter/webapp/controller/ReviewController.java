package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.services.impl.ReviewService;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 8/21/2017.
 */

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private IEntityFactory entityFactory;

    @RequestMapping(value = "/api/review", method = RequestMethod.POST)
    ResponseEntity addReview(RequestEntity<ReviewDTO> reviewDTO){

        reviewService.save(entityFactory.getReviewEntity(reviewDTO.getBody()));
        return new ResponseEntity(HttpStatus.CREATED);
    }
}