package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 8/16/2017.
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TeamRepository teamRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public PersonEntity getById(Integer personId) {
        return personRepository.findOne(personId);
    }

    @Override
    public PersonEntity getByLogin(String login) {

        return personRepository.findByLogin(login);

    }

    @Override
    public List<PersonEntity> getAllPersonsByProjectId(Integer projectId) {

        List<PersonEntity> personEntities = new ArrayList<>();

        List<TeamEntity> teamEntities = teamRepository.findAllByProjectId(projectId);

        for (int i = 0; i < teamEntities.size(); i++) {

            personEntities.add(personRepository.findOne(teamEntities.get(i).getPerson().getId()));
        }

        return personEntities;
    }

    @Override
    public List<PersonEntity> getAllPersonsByProjectId(Integer projectId, Boolean isMember) {

        List<PersonEntity> personEntities = new ArrayList<>();

        List<TeamEntity> teamEntities = teamRepository.findAllByProjectIdAndMember(projectId, isMember);

        for (int i = 0; i < teamEntities.size(); i++) {

            personEntities.add(personRepository.findOne(teamEntities.get(i).getPerson().getId()));
        }

        return personEntities;
    }

    @Override
    public PositionEntity getPositionOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId) {
        PositionEntity positionEntity = new PositionEntity();

        TeamEntity teamEntity = teamRepository.findByPersonIdAndProjectId(personId, projectId);

        return teamEntity.getPosition();
    }

    @Override
    public Boolean getStatusOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId) {

        TeamEntity teamEntity = teamRepository.findByPersonIdAndProjectId(personId, projectId);

        return teamEntity.getMember();
    }


    @Override
    public List<PersonEntity> searchPersons(
            String role,
            String fullName,
            Integer ageFrom,
            Integer ageTo,
            String country,
            String city,
            List<String> directions,
            List<String> skills,
            String educationName,
            Boolean isMentorExp) {

        if (skills == null) {
            skills = new ArrayList<>();
        }
        if (directions == null) {
            directions = new ArrayList<>();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonEntity> criteriaQuery = criteriaBuilder.createQuery(PersonEntity.class);

        Root<PersonEntity> root = criteriaQuery.from(PersonEntity.class);

        //Predicates
        Predicate rolePredicate = criteriaBuilder.conjunction();
        Predicate fullNamePredicate = criteriaBuilder.conjunction();
        Predicate ageFromPredicate = criteriaBuilder.conjunction();
        Predicate ageToPredicate = criteriaBuilder.conjunction();
        Predicate countryPredicate = criteriaBuilder.conjunction();
        Predicate cityPredicate = criteriaBuilder.conjunction();
        List<Predicate> directionPredicateList = new ArrayList<>();
        List<Predicate> skillPredicateList = new ArrayList<>();
        Predicate educationNamePredicate = criteriaBuilder.conjunction();
        Predicate isMentorExpPredicate = criteriaBuilder.conjunction();

        //Create Predicates
        if (role != null) {
            rolePredicate = criteriaBuilder.equal(root.join("role").get("name"), role);
        }

        if (fullName != null) {
            fullNamePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.join("contact").get("fullName")), "%" + fullName.toUpperCase() + "%");
        }

        if (ageFrom != null) {
            LocalDate localDate = LocalDate.now().minusYears(ageFrom);
            Timestamp timestampTo = Timestamp.valueOf(localDate.atStartOfDay());
            ageFromPredicate = criteriaBuilder.lessThanOrEqualTo(root.join("contact").get("dateOfBirth"), timestampTo);
        }
        if (ageTo != null) {
            LocalDate localDate = LocalDate.now().minusYears(ageTo);
            Timestamp timestampFrom = Timestamp.valueOf(localDate.atStartOfDay());
            ageToPredicate = criteriaBuilder.greaterThanOrEqualTo(root.join("contact").get("dateOfBirth"), timestampFrom);
        }
        if (skills.size() != 0) {
            for (int i = 0; i < skills.size(); i++) {
                skillPredicateList.add(criteriaBuilder.equal(root.join("profile").join("skills").get("name"), skills.get(i)));
            }
        } else {
            skillPredicateList.add(criteriaBuilder.conjunction());
        }
        if (country != null) {
            countryPredicate = criteriaBuilder.equal(root.join("contact").join("location").join("country").get("name"), country);
        }
        if (city != null) {
            cityPredicate = criteriaBuilder.equal(root.join("contact").join("location").join("city").get("name"), city);
        }
        if (educationName != null) {
            educationNamePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.join("profile").join("educations").get("name")), "%" + educationName.toUpperCase() + "%");
        }

        if (directions.size() != 0) {
            for (int i = 0; i < directions.size(); i++) {
                directionPredicateList.add(criteriaBuilder.equal(root.join("profile").join("direction").get("name"), directions.get(i)));
            }
        } else {
            directionPredicateList.add(criteriaBuilder.conjunction());
        }

        if (isMentorExp != null) {
            isMentorExpPredicate = criteriaBuilder.equal(root.join("profile").join("mentorInfo").get("mentorExp"), isMentorExp);
        }

        //Query
        criteriaQuery.select(root).distinct(true).where(criteriaBuilder.and(
                rolePredicate,
                fullNamePredicate,
                criteriaBuilder.or(skillPredicateList.toArray(new Predicate[]{})),
                ageFromPredicate,
                ageToPredicate,
                countryPredicate,
                cityPredicate,
                criteriaBuilder.or(directionPredicateList.toArray(new Predicate[]{})),
                educationNamePredicate,
                isMentorExpPredicate));

        TypedQuery<PersonEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        List<PersonEntity> personEntityList = typedQuery.getResultList();

        return personEntityList;

    }

    private Boolean save(PersonEntity personEntity) {



        if ((personEntity.getRole()==null)
                || (personEntity.getRole().getName().equals(RoleType.ROLE_ADMIN))
                ||(personEntity.getRole().getName().equals(RoleType.ROLE_MODER))) {

            return false;


        }

        if (personEntity.getRole().getName().equals(RoleType.ROLE_MENTOR)) {

            if ((personEntity.getProfile().getMentorInfo().getMentorExp()==null)
                    ||(personEntity.getProfile().getMentorInfo().getExperience()==null)) {

                return false;

            }


        } else {

            personEntity.getProfile().setMentorInfo(null);

        }


        if (personEntity.getRole().getName().equals(RoleType.ROLE_CUSTOMER)) {

            personEntity.setProfile(null);

        } else {


            if ((personEntity.getProfile().getDirection()==null)
                    ||(!directionRepository.exists(personEntity.getProfile().getDirection().getId()))) {

                return false;


            }

        }


        if (countryRepository.getByNameIgnoreCase(personEntity.getContact().getLocation().getCountry().getName())==null) {

            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setName(personEntity.getContact().getLocation().getCountry().getName());
            personEntity.getContact().getLocation().setCountry(countryRepository.save(countryEntity));

        } else {

            personEntity.getContact().getLocation().setCountry(
                    countryRepository.getByNameIgnoreCase(personEntity.getContact().getLocation().getCountry().getName()));

        }



        if (cityRepository.getByNameIgnoreCase(personEntity.getContact().getLocation().getCity().getName())==null) {

            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(personEntity.getContact().getLocation().getCity().getName());
            cityEntity.setCountry(personEntity.getContact().getLocation().getCountry());
            personEntity.getContact().getLocation().setCity(cityRepository.save(cityEntity));

        } else {



                personEntity.getContact().getLocation().setCity(
                        cityRepository.getByNameIgnoreCase(personEntity.getContact().getLocation().getCity().getName()));

        }


        //personEntity.getProfile().setId(personEntity.getId());
        //personEntity.getContact().setId(personEntity.getId());



        if (personRepository.save(personEntity)!=null) {

            return true;

        } else {

            return false;

        }



    }

    @Override
    @Transactional
    public Boolean create(PersonEntity personEntity) {

        if ((personEntity.getPassword()==null)
                ||(personRepository.findByLogin(personEntity.getLogin())!=null)) {

            return false;


        }


        personEntity.setId(null);
        personEntity.getProfile().setPerson(personEntity);


        List<EducationEntity> educationEntities=personEntity.getProfile().getEducations();

        for (int i=0; i<educationEntities.size(); i++) {

            educationEntities.get(i).setId(null);
            educationEntities.get(i).setProfile(personEntity.getProfile());

        }

        List<CourseEntity> courseEntities=personEntity.getProfile().getCourses();

        for (int i=0; i<courseEntities.size(); i++) {

            courseEntities.get(i).setId(null);
            courseEntities.get(i).setProfile(personEntity.getProfile());

        }

        List<WorkplaceEntity> workplaceEntities=personEntity.getProfile().getWorkplaces();

        for (int i=0; i<workplaceEntities.size(); i++) {

            workplaceEntities.get(i).setId(null);
            workplaceEntities.get(i).setProfile(personEntity.getProfile());

        }

        personEntity.getProfile().setSkills(personEntity.getProfile().getSkills());

        personEntity.setPassword(BCrypt.hashpw(personEntity.getPassword(),BCrypt.gensalt()));
        personEntity.setBlocked(false);
        personEntity.getProfile().setApproved(false);

        return save(personEntity);





    }

    @Override
    public Boolean update(PersonEntity personEntity) {

        personEntity.getContact().setId(personEntity.getId());

        return save(personEntity);
/*
        PersonEntity personInDatabase=personRepository.findOne(personEntity.getId());

        if (personInDatabase==null) {

            return false;

        }

        if (!personEntity.getLogin().equals(personInDatabase.getLogin())) {

            if (personRepository.findByLogin(personEntity.getLogin())!=null) {

                return false;


            }

        }



     //check educations   ---------------------------------------------------

        List<EducationEntity> educationEntities=personInDatabase.getProfile().getEducations();
        List<EducationEntity> bufEducations=new ArrayList<>();

        Set<Integer> set=new HashSet<>();

        for(int i=0; i<educationEntities.size(); i++) {

            set.add(educationEntities.get(i).getId());

        }

        for(int i=0; i<personEntity.getProfile().getEducations().size(); i++) {


            if ((personEntity.getProfile().getEducations().get(i).getId()==null)
                ||(set.contains(personEntity.getProfile().getEducations().get(i).getId()))) {

                if (!educationTypeRepository.exists(personEntity.getProfile().getEducations()
                        .get(i).getEducationTypeEntity().getId())) {

                    return false;

                }

                bufEducations.add(personEntity.getProfile().getEducations().get(i));

            }

        }

   //----------------------------------------------------

        // check course ---------------------------------------------

        List<CourseEntity> courseEntities=personInDatabase.getProfile().getCourses();
        List<CourseEntity> bufCourses=new ArrayList<>();

        set=new HashSet<>();

        for(int i=0; i<courseEntities.size(); i++) {

            set.add(courseEntities.get(i).getId());

        }

        for(int i=0; i<personEntity.getProfile().getCourses().size(); i++) {


            if ((personEntity.getProfile().getCourses().get(i).getId()==null)
                    ||(set.contains(personEntity.getProfile().getCourses().get(i).getId()))) {

                bufCourses.add(personEntity.getProfile().getCourses().get(i));

            }

        }
        //----------------------------------------------------

        //check workplaces ----------------------------------

        List<WorkplaceEntity> workplaceEntities=personInDatabase.getProfile().getWorkplaces();
        List<WorkplaceEntity> bufWorkplaces=new ArrayList<>();

        set=new HashSet<>();

        for(int i=0; i<workplaceEntities.size(); i++) {

            set.add(workplaceEntities.get(i).getId());

        }

        for(int i=0; i<personEntity.getProfile().getWorkplaces().size(); i++) {


            if ((personEntity.getProfile().getWorkplaces().get(i).getId()==null)
                    ||(set.contains(personEntity.getProfile().getWorkplaces().get(i).getId()))) {

                bufWorkplaces.add(personEntity.getProfile().getWorkplaces().get(i));

            }

        }
        //----------------------------------------------------

        //check skills --------------------------------------

        List<SkillEntity> bufSkills=new ArrayList<>();

        for(int i=0; i<personEntity.getProfile().getSkills().size();i++) {

            if (skillRepository.exists(personEntity.getProfile().getSkills().get(i).getId())) {

                bufSkills.add(personEntity.getProfile().getSkills().get(i));

            }

        }

        //----------------------------------------------------

        personEntity.getProfile().setId(personEntity.getId());
        personEntity.getProfile().setEducations(bufEducations);
        personEntity.getProfile().setCourses(bufCourses);
        personEntity.getProfile().setWorkplaces(bufWorkplaces);
        personEntity.getProfile().setSkills(bufSkills);

        personEntity.setPassword(personInDatabase.getPassword());
        personEntity.getProfile().setApproved(personInDatabase.getProfile().getApproved());
        personEntity.setBlocked(personInDatabase.getBlocked());

        return save(personEntity);*/


        //return null;

    }


}