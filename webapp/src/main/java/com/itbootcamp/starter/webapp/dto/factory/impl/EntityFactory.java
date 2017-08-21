package com.itbootcamp.starter.webapp.dto.factory.impl;

import com.itbootcamp.starter.datamodel.impl.ReviewEntity;
import com.itbootcamp.starter.services.IPersonService;
import com.itbootcamp.starter.services.IProjectService;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 8/21/2017.
 */
@Component
@Scope(value = "singleton")
public class EntityFactory implements IEntityFactory {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IPersonService personService;


    @Override
    public ReviewEntity getReviewEntity(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setDate(reviewDTO.getDate());
        reviewEntity.setProject(projectService.getById(reviewDTO.getProjectId()));
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setText(reviewDTO.getText());
        reviewEntity.setSenderPerson(personService.getById(reviewDTO.getSenderPersonId()));
        reviewEntity.setReceiverPerson(personService.getById(reviewDTO.getReceiverPersonId()));

        return reviewEntity;
    }
}
