package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.ReviewEntity;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.services.impl.ReviewService;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private IEntityFactory entityFactory;

    @Autowired
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/review", method = RequestMethod.POST)
    ResponseEntity addReview(@RequestBody @Valid ReviewDTO reviewDTO, BindingResult bindingResult,
                             OAuth2Authentication oAuth2Authentication){


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        PersonEntity personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());


        if (!reviewService.add(entityFactory.getReviewEntity(reviewDTO),personEntity)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(HttpStatus.CREATED);
    }



    @PreAuthorize("hasAnyAuthority('Moder')")
    @RequestMapping(value = "/api/review/{reviewId}", method = RequestMethod.DELETE)
    ResponseEntity addReview(@PathVariable Integer reviewID){

        if (!reviewService.isExist(reviewID)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }


        if (!reviewService.delete(reviewID)) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile/{personId}/reviews}", method = RequestMethod.GET)
    ResponseEntity getReviewsByPerson(@PathVariable Integer personId){

        PersonEntity personEntity=personService.getById(personId);

        if (personEntity==null) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        List<ReviewDTO> reviewsDTO=new ArrayList<>();
        List<ReviewEntity> reviewsEntity=personEntity.getReceiverReviews();

        for(ReviewEntity reviewEntity:reviewsEntity) {

            reviewsDTO.add(dtoFactory.getReviewDTO(reviewEntity));

        }

        return new ResponseEntity(reviewsDTO,HttpStatus.OK);

    }



}