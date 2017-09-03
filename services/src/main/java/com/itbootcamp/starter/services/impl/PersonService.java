package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IPersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
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

    private static final Logger logger = Logger.getLogger(Logger.class);

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

            if (!personEntity.getRole().getName().equals(RoleType.ROLE_CUSTOMER)) {

                personEntity.getProfile().setMentorInfo(null);

            }

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
            countryRepository.save(countryEntity);
            personEntity.getContact().getLocation().setCountry(countryEntity);

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


        for (EducationEntity educationEntity:personEntity.getProfile().getEducations()) {

            educationEntity.setId(null);
            educationEntity.setProfile(personEntity.getProfile());

        }

        for (CourseEntity courseEntity:personEntity.getProfile().getCourses()) {

            courseEntity.setId(null);
            courseEntity.setProfile(personEntity.getProfile());

        }


        for (WorkplaceEntity workplaceEntity:personEntity.getProfile().getWorkplaces()) {

            workplaceEntity.setId(null);
            workplaceEntity.setProfile(personEntity.getProfile());

        }

        personEntity.getProfile().setSkills(personEntity.getProfile().getSkills());
        if ((personEntity.getContact().getAvatarPath()==null)
                ||(personEntity.getContact().getAvatarPath().isEmpty())) {

            personEntity.getContact().setAvatarPath("https://starter-itbootcamp.herokuapp.com/avatar/default-avatar.png");

        }

        personEntity.setPassword(BCrypt.hashpw(personEntity.getPassword(),BCrypt.gensalt()));
        personEntity.setBlocked(false);
        personEntity.getProfile().setApproved(false);

        return save(personEntity);





    }

    @Override
    @Transactional
    public Boolean update(PersonEntity personEntity,ContactEntity contactEntity) {

        personEntity.getContact().setAvatarPath(contactEntity.getAvatarPath());
        personEntity.getContact().setDateOfBirth(contactEntity.getDateOfBirth());
        personEntity.getContact().setPhone(contactEntity.getPhone());
        personEntity.getContact().setSkype(contactEntity.getSkype());
        personEntity.getContact().setAvatarPath(contactEntity.getAvatarPath());
        personEntity.getContact().setEmail(contactEntity.getEmail());
        personEntity.getContact().setAbout(contactEntity.getAbout());
        personEntity.getContact().setFullName(contactEntity.getFullName());

        if (countryRepository.getByNameIgnoreCase(contactEntity.getLocation().getCountry().getName())==null) {

            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setName(contactEntity.getLocation().getCountry().getName());
            countryRepository.save(countryEntity);
            personEntity.getContact().getLocation().setCountry(countryEntity);

        } else {

            personEntity.getContact().getLocation().setCountry(
                    countryRepository.getByNameIgnoreCase(contactEntity.getLocation().getCountry().getName()));

        }



        if (cityRepository.getByNameIgnoreCase(contactEntity.getLocation().getCity().getName())==null) {

            CityEntity cityEntity = new CityEntity();
            cityEntity.setName(contactEntity.getLocation().getCity().getName());
            cityEntity.setCountry(personEntity.getContact().getLocation().getCountry());
            personEntity.getContact().getLocation().setCity(cityRepository.save(cityEntity));

        } else {



            personEntity.getContact().getLocation().setCity(
                    cityRepository.getByNameIgnoreCase(contactEntity.getLocation().getCity().getName()));

        }


        if (personRepository.save(personEntity)!=null) {

            return true;

        } else {

            return false;

        }

    }

    @Override
    public Boolean blocked(PersonEntity personEntity) {



        personEntity.setBlocked(!personEntity.getBlocked());

        if (personRepository.save(personEntity)!=null) {

            return true;

        }

        return false;

    }

    @Override
    public Boolean approwed(PersonEntity personEntity) {

        if ((!personEntity.getRole().getName().equals(RoleType.ROLE_MENTOR))
            &&(!personEntity.getRole().getName().equals(RoleType.ROLE_TRAINEE))) {

            return false;

        }

        personEntity.getProfile().setApproved(!personEntity.getProfile().getApproved());

        if (personRepository.save(personEntity)!=null) {

            return true;

        }

        return false;
    }


}