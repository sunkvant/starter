package com.itbootcamp.starter.webapp.dto.factory.impl;

import com.itbootcamp.starter.datamodel.impl.LanguageEntity;
import com.itbootcamp.starter.datamodel.impl.ProjectCategoryEntity;
import com.itbootcamp.starter.datamodel.impl.ProjectStatusEntity;
import com.itbootcamp.starter.datamodel.impl.ReviewEntity;
import com.itbootcamp.starter.services.*;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import com.itbootcamp.starter.webapp.dto.SearchProjectDTO;
import com.itbootcamp.starter.services.SearchProjectEntity;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IProjectCategoryService projectCategoryService;

    @Autowired
    private IProjectStatusService projectStatusService;

    @Autowired
    private ILanguageService languageService;

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

    @Override
    public SearchProjectEntity getSearchProjectEntity(SearchProjectDTO searchProjectDTO){
        SearchProjectEntity searchProjectEntity = new SearchProjectEntity();
        searchProjectEntity.setName(searchProjectDTO.getName());
        searchProjectEntity.setDateStart(searchProjectDTO.getDateStart());
        searchProjectEntity.setDateEnd(searchProjectDTO.getDateEnd());

        List<ProjectCategoryEntity> projectCategoryEntityList = new ArrayList<>();
        for (int i = 0; i < searchProjectDTO.getProjectCategories().size(); i++) {
            projectCategoryEntityList.add(projectCategoryService.getByName(searchProjectDTO.getProjectCategories().get(i)));
        }
        searchProjectEntity.setProjectCategories(projectCategoryEntityList);

        List<ProjectStatusEntity> projectStatusEntityList = new ArrayList<>();
        for (int i = 0; i < searchProjectDTO.getProjectStatuses().size(); i++) {
            projectStatusEntityList.add(projectStatusService.getByName(searchProjectDTO.getProjectStatuses().get(i)));
        }
        searchProjectEntity.setProjectStatuses(projectStatusEntityList);

        List<LanguageEntity> languageEntityList = new ArrayList<>();
        if (searchProjectDTO.getLanguages() != null) {
            for (int i = 0; i < searchProjectDTO.getLanguages().size(); i++) {
                languageEntityList.add(languageService.getByName(searchProjectDTO.getLanguages().get(i)));
            }
        }
        searchProjectEntity.setLanguages(languageEntityList);

        return searchProjectEntity;
    }

}
