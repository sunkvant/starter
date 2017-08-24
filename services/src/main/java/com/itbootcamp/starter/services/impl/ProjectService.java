package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.repository.ProjectRepository;
import com.itbootcamp.starter.repository.TeamRepository;
import com.itbootcamp.starter.services.IProjectService;
import com.itbootcamp.starter.services.SearchProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:search.sql.properties")
public class ProjectService implements IProjectService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${seacrhProjectSql}")
    private String sql;


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
    public List<ProjectEntity> searchProjects(SearchProjectEntity searchProjectEntity) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectEntity> criteriaQuery = criteriaBuilder.createQuery(ProjectEntity.class);

        Root<ProjectEntity> root = criteriaQuery.from(ProjectEntity.class);
        Join<ProjectEntity, LanguageEntity> languagesJoin = root.join("languages");


        Predicate namePredicate = null;
        List<Predicate> projectCategoryPredicateList = new ArrayList<>();
        List<Predicate> projectStatusPredicateList = new ArrayList<>();
        List<Predicate> languagePredicateList = new ArrayList<>();


        if (searchProjectEntity.getName() != null) {
            namePredicate = criteriaBuilder.equal(root.get("name"), searchProjectEntity.getName());
        } else {
            namePredicate = criteriaBuilder.conjunction();
        }

        if ((searchProjectEntity.getProjectCategories() != null) || (searchProjectEntity.getProjectCategories().size() != 0)) {
            for (int i = 0; i < searchProjectEntity.getProjectCategories().size(); i++) {
                if (searchProjectEntity.getProjectCategories().get(i) != null) {
                    projectCategoryPredicateList.add(criteriaBuilder.equal(root.join("projectCategory").get("category"), searchProjectEntity.getProjectCategories().get(i).getCategory()));
                }
            }
        } else {
            projectCategoryPredicateList.add(criteriaBuilder.conjunction());
        }

        if (searchProjectEntity.getProjectStatuses() != null) {
            if (searchProjectEntity.getProjectStatuses().size() != 0) {
                for (int i = 0; i < searchProjectEntity.getProjectStatuses().size(); i++) {
                    if (searchProjectEntity.getProjectStatuses().get(i) != null) {
                        projectStatusPredicateList.add(criteriaBuilder.equal(root.join("projectStatus").get("status"), searchProjectEntity.getProjectStatuses().get(i).getStatus()));
                    }
                }
            }
        } else {
            projectStatusPredicateList.add(criteriaBuilder.conjunction());
        }

        Boolean con=false;

        if ((searchProjectEntity.getLanguages() == null)||(searchProjectEntity.getLanguages().size() == 0)) {
            languagePredicateList.add(criteriaBuilder.conjunction());
        } else {
            for (int i = 0; i < searchProjectEntity.getLanguages().size(); i++) {
                if (searchProjectEntity.getLanguages().get(i) != null)
                    languagePredicateList.add(criteriaBuilder.equal(root.join("languages").get("name"), searchProjectEntity.getLanguages().get(i).getName()));
            }
        }


        criteriaQuery.select(root).distinct(true).where(criteriaBuilder.and(namePredicate,
                criteriaBuilder.or(projectCategoryPredicateList.toArray(new Predicate[]{})),
                criteriaBuilder.or(projectStatusPredicateList.toArray(new Predicate[]{}))),
                criteriaBuilder.or(languagePredicateList.toArray(new Predicate[]{})));

        TypedQuery<ProjectEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        List<ProjectEntity> projectEntityList = typedQuery.getResultList();

        return projectEntityList;
    }
}
