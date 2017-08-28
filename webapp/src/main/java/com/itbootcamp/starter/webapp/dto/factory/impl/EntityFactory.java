package com.itbootcamp.starter.webapp.dto.factory.impl;


import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.repository.SkillRepository;
import com.itbootcamp.starter.services.*;
import com.itbootcamp.starter.webapp.dto.*;
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

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ProfileRepository profileRepository;

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


    public RoleEntity getRoleEntity(RoleDTO roleDTO) {

        if (roleDTO==null) {

            return null;

        }

        RoleEntity roleEntity=new RoleEntity();

        roleEntity.setId(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());

        return roleEntity;


    }

    public DirectionEntity getDirectionEntity(DirectionDTO directionDTO) {



        if (directionDTO==null) {

            return null;

        }

        DirectionEntity directionEntity=new DirectionEntity();

        directionEntity.setId(directionDTO.getId());
        directionEntity.setName(directionDTO.getName());

        return directionEntity;
    }

    public EducationTypeEntity getEducationTypeEntity(EducationTypeDTO educationTypeDTO) {

        if (educationTypeDTO==null) {

            return null;

        }

        EducationTypeEntity educationTypeEntity=new EducationTypeEntity();

        educationTypeEntity.setId(educationTypeDTO.getId());
        educationTypeEntity.setType(educationTypeDTO.getName());

        return educationTypeEntity;


    }

    @Override
    public EducationEntity getEducationEntity(EducationDTO educationDTO) {


        if (educationDTO==null) {

            return null;


        }


        EducationEntity educationEntity=new EducationEntity();

        educationEntity.setId(educationDTO.getId());
        educationEntity.setName(educationDTO.getName());
        educationEntity.setFaculty(educationDTO.getFaculty());
        educationEntity.setSpeciality(educationDTO.getSpeciality());
        educationEntity.setGraduationYear(educationDTO.getGraduationYear());
        educationEntity.setEducationTypeEntity(getEducationTypeEntity(educationDTO.getEducationType()));

        return educationEntity;
    }

    @Override
    public CourseEntity getCourseEntity(CourseDTO courseDTO) {


        if (courseDTO==null) {

            return null;


        }

        CourseEntity courseEntity=new CourseEntity();

        courseEntity.setId(courseDTO.getId());
        courseEntity.setName(courseDTO.getName());
        courseEntity.setOrganization(courseDTO.getOrganization());
        courseEntity.setSpeciality(courseDTO.getSpeciality());
        courseEntity.setGraduationYear(courseDTO.getGraduationYear());

        return courseEntity;
    }

    @Override
    public WorkplaceEntity getWorkplaceEntity(WorkplaceDTO workplaceDTO) {

        if (workplaceDTO==null) {

            return null;


        }

        WorkplaceEntity workplaceEntity=new WorkplaceEntity();

        workplaceEntity.setId(workplaceDTO.getId());
        workplaceEntity.setCompany(workplaceDTO.getCompany());
        workplaceEntity.setSphereOfActivity(workplaceDTO.getSphereOfActivity());
        workplaceEntity.setPosition(workplaceDTO.getPosition());
        workplaceEntity.setDuties(workplaceDTO.getDuties());
        workplaceEntity.setStartWork(workplaceDTO.getStartWork());
        workplaceEntity.setEndWork(workplaceDTO.getEndWork());
        workplaceEntity.setWorking(workplaceDTO.getWorking());

        return  workplaceEntity;


    }

    @Override
    public List<SkillEntity> getSkillsEntity(List<SkillDTO> skillsDTO) {

        if (skillsDTO==null) {

            return null;

        }


        List<SkillEntity> skillEntities=new ArrayList<>();



        for(int i=0; i<skillsDTO.size(); i++) {

            SkillEntity skillEntity=new SkillEntity();

            skillEntity.setId(skillsDTO.get(i).getId());
            skillEntity.setName(skillsDTO.get(i).getName());

            skillEntities.add(skillEntity);

        }

        return skillEntities;

    }

    @Override
    public LocationEntity getLocationEntity(LocationDTO locationDTO) {

        if (locationDTO==null) {

            return null;

        }

        LocationEntity locationEntity=new LocationEntity();

        locationEntity.setCity(getCityEntity(locationDTO.getCity()));
        locationEntity.setCountry(getCountryEntity(locationDTO.getCountry()));

        return locationEntity;
    }

    @Override
    public CityEntity getCityEntity(CityDTO cityDTO) {

        CityEntity cityEntity=new CityEntity();

        cityEntity.setId(cityDTO.getId());
        cityEntity.setName(cityDTO.getName());

        return cityEntity;

    }

    @Override
    public CountryEntity getCountryEntity(CountryDTO countryDTO) {
        CountryEntity countryEntity=new CountryEntity();

        countryEntity.setId(countryDTO.getId());
        countryEntity.setName(countryDTO.getName());

        return countryEntity;
    }

    @Override
    public ContactEntity getContactEntity(ContactDTO contactDTO) {

        ContactEntity contactEntity=new ContactEntity();

        contactEntity.setFullName(contactDTO.getFullName());
        contactEntity.setDateOfBirth(contactDTO.getDateOfBirth());
        contactEntity.setAvatarPath(contactDTO.getAvatarPath());
        contactEntity.setPhone(contactDTO.getPhone());
        contactEntity.setSkype(contactDTO.getSkype());
        contactEntity.setEmail(contactDTO.getEmail());
        contactEntity.setAbout(contactDTO.getAbout());
        contactEntity.setLocation(getLocationEntity(contactDTO.getLocation()));

        return contactEntity;
    }


    @Override
    public PersonEntity getPersonEntity(ProfileDTO profileDTO)  {

        PersonEntity personEntity=new PersonEntity();

        personEntity.setId(profileDTO.getId());
        personEntity.setLogin(profileDTO.getLogin());
        personEntity.setPassword(profileDTO.getPassword());
        personEntity.setBlocked(profileDTO.getBlocked());
        personEntity.setRole(getRoleEntity(profileDTO.getRole()));

        ContactEntity contactEntity=getContactEntity(profileDTO.getContact());

        personEntity.setContact(contactEntity);
        contactEntity.setPerson(personEntity);

        ProfileEntity profileEntity=new ProfileEntity();
        profileEntity.setApproved(profileDTO.getApproved());
        profileEntity.setDirection(getDirectionEntity(profileDTO.getDirection()));



        List<EducationEntity> educationEntities=new ArrayList<>();

        for(int i=0; i<profileDTO.getEducations().size(); i++) {

            educationEntities.add(getEducationEntity(profileDTO.getEducations().get(i)));

        }




        List<CourseEntity> courseEntities=new ArrayList<>();

        for(int i=0; i<profileDTO.getCourses().size(); i++) {

            courseEntities.add(getCourseEntity(profileDTO.getCourses().get(i)));

        }



        List<WorkplaceEntity> workplaceEntities=new ArrayList<>();


        for(int i=0; i<profileDTO.getWorkplaces().size(); i++) {


            workplaceEntities.add(getWorkplaceEntity(profileDTO.getWorkplaces().get(i)));

        }




        List<SkillEntity> skillEntities=getSkillsEntity(profileDTO.getSkills());


        MentorInfoEntity mentorInfoEntity=new MentorInfoEntity();

        mentorInfoEntity.setExperience(profileDTO.getExperience());
        mentorInfoEntity.setMentorExp(profileDTO.getMentorExp());
        mentorInfoEntity.setProfile(profileEntity);

        profileEntity.setMentorInfo(mentorInfoEntity);
        profileEntity.setCourses(courseEntities);
        profileEntity.setWorkplaces(workplaceEntities);
        profileEntity.setEducations(educationEntities);
        profileEntity.setSkills(skillEntities);

        personEntity.setProfile(profileEntity);

        return personEntity;
    }

}
