package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.ProjectRepository;
import com.itbootcamp.starter.repository.TeamRepository;
import com.itbootcamp.starter.repository.VacancyRepository;
import com.itbootcamp.starter.services.IProjectService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
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

    @Autowired
    private ProjectStatusService projectStatusService;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MessageRequestService messageRequestService;

    @Override
    public ProjectEntity getById(Integer projectId) {
        return projectRepository.findOne(projectId);
    }

    @Override
    public List<ProjectEntity> getAllProjectsByPersonId(Integer personId) {

        List<TeamEntity> teamEntities = teamRepository.findAllByPersonId(personId);
        List<ProjectEntity> projectEntityList = new ArrayList<>();

        for (TeamEntity team:teamEntities) {
            projectEntityList.add(projectRepository.findOne(team.getProject().getId()));
        }
        return projectEntityList;
    }

    @Override
    public List<ProjectEntity> getAllProjectsByPersonId(Integer personId, Boolean isMember) {

        List<TeamEntity> teamEntities = teamRepository.findAllByPersonIdAndMember(personId, isMember);
        List<ProjectEntity> projectEntityList = new ArrayList<>();

        for (TeamEntity team:teamEntities) {
            projectEntityList.add(projectRepository.findOne(team.getProject().getId()));
        }

        return projectEntityList;
    }

    @Override
    public Boolean isExist(Integer projectId) {
        return projectRepository.exists(projectId);
    }

    @Override
    public Boolean isMember(PersonEntity personEntity, ProjectEntity projectEntity) {

        if (teamRepository.findByProjectIdAndPersonIdAndMember(projectEntity.getId(),personEntity.getId(),true)!=null) {

            return true;

        }


        return false;
    }

    @Override
    public Boolean create(ProjectEntity projectEntity,PersonEntity personEntity) {


        if ((projectEntity.getProjectCategory()==null)) {

            return false;

        }

        projectEntity.setDateStart(new Timestamp(System.currentTimeMillis()));
        projectEntity.setDateEnd(null);
        projectEntity.setProjectStatus(projectStatusService.getByName(ProjectStatus.RECRUITING));
        projectEntity.setCustomer(personEntity);

        if (projectRepository.save(projectEntity)!=null) {


            return true;

        }

        return false;
    }

    @Override
    @Transactional
    public Boolean update(ProjectEntity projectEntity,ProjectEntity projectEntityOld) {


        Boolean isChangeStatus = false;
        String changedStatus="";

        if ((projectEntity.getProjectStatus()==null)||(projectEntity.getProjectCategory()==null)) {

            return false;

        }

        if (!projectEntity.getProjectStatus().getStatus().equals(projectEntityOld.getProjectStatus().getStatus())) {

            isChangeStatus=true;
            changedStatus=projectEntity.getProjectStatus().getStatus();


        }

        projectEntityOld.setName(projectEntity.getName());
        projectEntityOld.setContactInfo(projectEntity.getContactInfo());
        projectEntityOld.setLanguages(projectEntity.getLanguages());
        projectEntityOld.setDescription(projectEntity.getDescription());
        projectEntityOld.setProjectStatus(projectEntity.getProjectStatus());

        if (isChangeStatus) {

            List<PersonEntity> listPersons = personService.getAllPersonsByProjectId(projectEntity.getId(), true);

            if (listPersons != null) {

                for (PersonEntity person : listPersons) {


                    if (changedStatus.equals(ProjectStatus.RECRUITING)) {
                        System.out.println("ERVEVVEVEVEVEVEVEVEVEVEV");
                        messageRequestService.save(person.getId(),
                                "Статус проекта.",
                                "Внимание! Был изменен статус проекта " + projectEntity.getName() + " на Набор команды",
                                projectEntityOld.getCustomer());
                    }


                    if (changedStatus.equals(ProjectStatus.DEVELOP)) {
                        messageRequestService.save(person.getId(),
                                "Статус проекта.",
                                "Внимание! Был изменен статус проекта " + projectEntity.getName() + " на В разработке.",
                                projectEntityOld.getCustomer());
                    }

                }

            }
        }

        if (projectRepository.save(projectEntityOld)!=null) {

            return true;

        }

        return false;
    }

    @Override
    @Transactional
    public Boolean addMember(VacancyEntity vacancyEntity, PersonEntity personEntity) {

        TeamEntity teamEntity=new TeamEntity();

        teamEntity.setId(null);
        teamEntity.setMember(true);
        teamEntity.setProject(vacancyEntity.getProject());
        teamEntity.setPosition(vacancyEntity.getPosition());
        teamEntity.setPerson(personEntity);

        if (vacancyEntity.getPersonNumber()==1) {

            vacancyService.delete(vacancyEntity,vacancyEntity.getProject());

        } else {

            vacancyEntity.setPersonNumber(vacancyEntity.getPersonNumber()-1);
            vacancyService.update(vacancyEntity,vacancyEntity.getProject());

        }


        messageRequestService.save(personEntity.getId(),
                "Статус проекта.",
                "Внимание! Вы были приняты на проект "+ vacancyEntity.getProject().getName()+" в качестве "+vacancyEntity.getPosition().getName(),
                vacancyEntity.getProject().getCustomer());


        teamRepository.save(teamEntity);

        return true;
    }

    @Override
    @Transactional
    public Boolean closeProject(ProjectEntity projectEntity) {


        while(!projectEntity.getVacancies().isEmpty()) {

            vacancyService.delete(projectEntity.getVacancies().get(0),projectEntity);


        }

        List<TeamEntity> teamEntities=teamRepository.findAllByProjectId(projectEntity.getId());

        for (TeamEntity team:teamEntities) {

            team.setMember(false);
            teamRepository.save(team);

        }


        projectEntity.setProjectStatus(projectStatusService.getByName(ProjectStatus.CLOSE));
        projectEntity.setDateEnd(new Timestamp(System.currentTimeMillis()));

        if (projectRepository.save(projectEntity)!=null) {

            List<PersonEntity> listPersons=personService.getAllPersonsByProjectId(projectEntity.getId(),true);

            if (listPersons!=null) {

                for(PersonEntity person:listPersons) {


                    if (projectEntity.getProjectStatus().getStatus().equals(ProjectStatus.CLOSE)) {
                        messageRequestService.save(person.getId(),
                                "Статус проекта.",
                                "Внимание! Был изменен статус проекта " + projectEntity.getName() + " на Закрыт. Теперь вы можете оставить отзывы, перейдя на страницу проекта.",
                                projectEntity.getCustomer());
                    }

                }


            }

            return true;

        }


        return false;


    }

    @Override
    public Boolean deleteMember(ProjectEntity projectEntity, PersonEntity member) {

        if (!isMember(member,projectEntity)) {



            return false;


        }

        messageRequestService.save(member.getId(),
                    "Статус проекта.",
                    "Внимание! Вы были исключены из проекта " + projectEntity.getName(),
                    projectEntity.getCustomer());


        TeamEntity teamEntity=teamRepository.findByPersonIdAndProjectId(member.getId(),projectEntity.getId());

        teamEntity.setMember(false);

        teamRepository.save(teamEntity);


        return true;
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