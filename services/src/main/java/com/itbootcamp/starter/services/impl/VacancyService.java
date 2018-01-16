package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.ProjectEntity;
import com.itbootcamp.starter.datamodel.RoleType;
import com.itbootcamp.starter.datamodel.VacancyEntity;
import com.itbootcamp.starter.repository.ProjectRepository;
import com.itbootcamp.starter.repository.VacancyRepository;
import com.itbootcamp.starter.services.IVacancyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
@Service
public class VacancyService implements IVacancyService {


    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private static final Logger logger = Logger.getLogger(Logger.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public VacancyEntity getById(Integer vacancyId) {

        return vacancyRepository.findOne(vacancyId);

    }

    @Override
    public List<VacancyEntity> getAllByProjectId(Integer projectId) {

        return vacancyRepository.findByProjectId(projectId);
    }

    @Override
    public Boolean add(VacancyEntity vacancyEntity, ProjectEntity projectEntity) {

        if ((vacancyEntity.getPosition()==null)||(vacancyEntity.getRole()==null)) {

            logger.error("Position or role does not exist in database.");

            return false;


        }

        if (!(vacancyEntity.getRole().getName().equals(RoleType.ROLE_MENTOR))
                &&(!vacancyEntity.getRole().getName().equals(RoleType.ROLE_TRAINEE))) {

            logger.error("Incorrect role type when adding vacancy.");

            return false;

        }


        vacancyEntity.setId(null);
        vacancyEntity.setProject(projectEntity);
        vacancyEntity.setActive(true);

        projectEntity.getVacancies().add(vacancyEntity);

        if (projectRepository.save(projectEntity)!=null) {

            logger.error("Vacancy was save in database.");
            return true;

        }

        logger.error("Vacancy was not save in database.");
        return false;
    }

    @Override
    public Boolean update(VacancyEntity vacancyEntity,ProjectEntity projectEntity) {

        if ((vacancyEntity.getPosition()==null)||(vacancyEntity.getRole()==null)) {

            logger.error("Position or role does not exist in database.");

            return false;


        }

        if (!(vacancyEntity.getRole().getName().equals(RoleType.ROLE_MENTOR))
                &&(!vacancyEntity.getRole().getName().equals(RoleType.ROLE_TRAINEE))) {

            logger.error("Incorrect role type when adding vacancy.");

            return false;

        }

        for(VacancyEntity vacancy:projectEntity.getVacancies()) {


            if (vacancyEntity.getId().equals(vacancy.getId())) {


                vacancy.setPersonNumber(vacancyEntity.getPersonNumber());
                vacancy.setPosition(vacancyEntity.getPosition());
                vacancy.setRole(vacancyEntity.getRole());
                vacancy.setLanguages(vacancyEntity.getLanguages());
                vacancy.setSkills(vacancyEntity.getSkills());

                if (projectRepository.save(projectEntity)!=null) {

                    return true;

                }

            }


        }

        logger.error("Vacancy with id="+vacancyEntity.getId()+" does not exist in project with id="+projectEntity.getId());
        return false;
    }

    @Override
    public Boolean delete(VacancyEntity vacancyEntity, ProjectEntity projectEntity) {

        for(VacancyEntity vacancy:projectEntity.getVacancies()) {

            if (vacancyEntity.getId().equals(vacancy.getId())) {

                vacancy.setActive(false);

                return true;

/*                projectEntity.getVacancies().remove(vacancy);
                if (projectRepository.save(projectEntity)!=null) {



                }*/

            }

        }


        return false;
    }

    @Override
    public List<VacancyEntity> searchVacancies(
                            List<String> positions,
                            String role,
                            List<String> skills,
                            List<String> languages) {


        if (positions == null) {
            positions = new ArrayList<>();
        }
        if (skills == null) {
            skills = new ArrayList<>();
        }
        if (languages == null) {
            languages = new ArrayList<>();
        }


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VacancyEntity> criteriaQuery = criteriaBuilder.createQuery(VacancyEntity.class);

        Root<VacancyEntity> root = criteriaQuery.from(VacancyEntity.class);

        //Predicates
        List<Predicate> positionPredicateList = new ArrayList<>();
        Predicate rolePredicate = criteriaBuilder.conjunction();
        List<Predicate> skillPredicateList = new ArrayList<>();
        List<Predicate> languagePredicateList = new ArrayList<>();


        //Create Predicates

        if (positions.size() != 0) {
            for (int i = 0; i < positions.size(); i++) {
                positionPredicateList.add(criteriaBuilder.equal(root.join("position").get("name"), positions.get(i)));
            }
        }else {
            positionPredicateList.add(criteriaBuilder.conjunction());
        }

        if (role != null) {
            rolePredicate = criteriaBuilder.equal(root.join("role").get("name"), role);
        }

        if (skills.size() != 0) {
            for (int i = 0; i < skills.size(); i++) {
                skillPredicateList.add(criteriaBuilder.equal(root.join("skills").get("name"), skills.get(i)));
            }
        }else {
            skillPredicateList.add(criteriaBuilder.conjunction());
        }

        if (languages.size() != 0) {
            for (int i = 0; i < languages.size(); i++) {
                languagePredicateList.add(criteriaBuilder.equal(root.join("languages").get("name"), languages.get(i)));
            }
        }else {
            languagePredicateList.add(criteriaBuilder.conjunction());
        }

        //Query
        criteriaQuery.select(root).distinct(true).where(criteriaBuilder.and(
                rolePredicate,
                criteriaBuilder.or(skillPredicateList.toArray(new Predicate[]{})),
                criteriaBuilder.or(positionPredicateList.toArray(new Predicate[]{})),
                criteriaBuilder.or(languagePredicateList.toArray(new Predicate[]{}))
        ));

        TypedQuery<VacancyEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        List<VacancyEntity> vacancyEntityList = typedQuery.getResultList();

        return vacancyEntityList;
    }
}
