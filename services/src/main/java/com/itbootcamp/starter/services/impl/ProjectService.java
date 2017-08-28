package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.ProjectEntity;
import com.itbootcamp.starter.datamodel.TeamEntity;
import com.itbootcamp.starter.repository.ProjectRepository;
import com.itbootcamp.starter.repository.TeamRepository;
import com.itbootcamp.starter.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/16/2017.
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProjectEntity getById(Integer projectId) {
        return projectRepository.findOne(projectId);
    }

    @Override
    public List<ProjectEntity> getAllProjectsByPersonId(Integer personId) {

        List<TeamEntity> teamEntities = teamRepository.findAllByPersonId(personId);
        List<ProjectEntity> projectEntityList = new ArrayList<>();

        for (int i = 0; i < teamEntities.size(); i++) {
            projectEntityList.add(projectRepository.findOne(teamEntities.get(i).getProject().getId()));
        }
        return projectEntityList;
    }

    @Override
    public List<ProjectEntity> getAllProjectsByPersonId(Integer personId, Boolean isMember) {

        List<TeamEntity> teamEntities = teamRepository.findAllByPersonIdAndMember(personId, isMember);
        List<ProjectEntity> projectEntityList = new ArrayList<>();

        for (int i = 0; i < teamEntities.size(); i++) {
            projectEntityList.add(projectRepository.findOne(teamEntities.get(i).getProject().getId()));
        }

        return projectEntityList;
    }

    @Override
    public Boolean isExist(Integer projectId) {
        return projectRepository.exists(projectId);
    }

    @Override
    public List<ProjectEntity> searchProjects(String projectName,
                                              List<String> projectCategoryList,
                                              List<String> projectStatusList,
                                              List<String> projectLanguageList) {

        if (projectCategoryList == null) {
            projectCategoryList = new ArrayList<>();
        }
        if (projectStatusList == null) {
            projectStatusList = new ArrayList<>();
        }
        if (projectLanguageList == null) {
            projectLanguageList = new ArrayList<>();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectEntity> criteriaQuery = criteriaBuilder.createQuery(ProjectEntity.class);

        Root<ProjectEntity> root = criteriaQuery.from(ProjectEntity.class);

        //Predicates
        Predicate namePredicate = criteriaBuilder.conjunction();
        List<Predicate> projectCategoryPredicateList = new ArrayList<>();
        List<Predicate> projectStatusPredicateList = new ArrayList<>();
        List<Predicate> languagePredicateList = new ArrayList<>();

        //Create Predicates
        if (projectName != null) {
            namePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + projectName.toUpperCase() + "%");
        }

        if (projectCategoryList.size() != 0) {
            for (int i = 0; i < projectCategoryList.size(); i++) {
                projectCategoryPredicateList.add(criteriaBuilder.equal(root.join("projectCategory").get("category"), projectCategoryList.get(i)));
            }
        } else {
            projectCategoryPredicateList.add(criteriaBuilder.conjunction());
        }

        if (projectStatusList.size() != 0) {
            for (int i = 0; i < projectStatusList.size(); i++) {
                projectStatusPredicateList.add(criteriaBuilder.equal(root.join("projectStatus").get("status"), projectStatusList.get(i)));
            }
        } else {
            projectStatusPredicateList.add(criteriaBuilder.conjunction());
        }

        if (projectLanguageList.size() != 0) {
            for (int i = 0; i < projectLanguageList.size(); i++) {
                languagePredicateList.add(criteriaBuilder.equal(root.join("languages").get("name"), projectLanguageList.get(i)));

            }
        } else {
            languagePredicateList.add(criteriaBuilder.conjunction());
        }

        //Query
        criteriaQuery.select(root).distinct(true).where(criteriaBuilder.and(namePredicate,
                criteriaBuilder.or(projectCategoryPredicateList.toArray(new Predicate[]{})),
                criteriaBuilder.or(projectStatusPredicateList.toArray(new Predicate[]{}))),
                criteriaBuilder.or(languagePredicateList.toArray(new Predicate[]{})));

        TypedQuery<ProjectEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        List<ProjectEntity> projectEntityList = typedQuery.getResultList();

        return projectEntityList;
    }
}