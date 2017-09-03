package com.itbootcamp.starter.webapp.dto.factory.impl;


import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.repository.SkillRepository;
import com.itbootcamp.starter.services.*;
import com.itbootcamp.starter.services.impl.*;
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
    private CityService cityService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private IDirectionService directionService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private RequestTypeService requestTypeService;

    @Override
    public ReviewEntity getReviewEntity(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setProject(projectService.getById(reviewDTO.getProjectId()));
        reviewEntity.setRating(reviewDTO.getRating());
        reviewEntity.setText(reviewDTO.getText());
        reviewEntity.setReceiverPerson(personService.getById(reviewDTO.getReceiverPersonId()));

        return reviewEntity;
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
        educationEntity.setEducationType(educationDTO.getEducationType());

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
    public List<SkillEntity> getSkillsEntity(List<String> skills) {


        List<SkillEntity> skillEntities=new ArrayList<>();

        for(int i=0; i<skills.size(); i++) {

            SkillEntity skillEntity=skillService.getByName(skills.get(i));

            if (skillEntity!=null) {

                skillEntities.add(skillEntity);

            }

        }

        return skillEntities;

    }

    @Override
    public List<LanguageEntity> getLanguagesEntity(List<String> languages) {

        List<LanguageEntity> languagesEntities=new ArrayList<>();

        for(int i=0; i<languages.size(); i++) {

            LanguageEntity languageEntity=languageService.getByName(languages.get(i));

            if (languageEntity!=null) {

                languagesEntities.add(languageEntity);

            }

        }

        return languagesEntities;
    }

    @Override
    public LocationEntity getLocationEntity(LocationDTO locationDTO) {

        if (locationDTO==null) {

            return null;

        }

        LocationEntity locationEntity=new LocationEntity();

        CityEntity cityEntity=new CityEntity();
        CountryEntity countryEntity=new CountryEntity();

        cityEntity.setName(locationDTO.getCity());
        countryEntity.setName(locationDTO.getCountry());

        locationEntity.setCity(cityEntity);
        locationEntity.setCountry(countryEntity);

        return locationEntity;
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
    public ProjectEntity getProjectEntity(ProjectDTO projectDTO) {

        ProjectEntity projectEntity=new ProjectEntity();

        projectEntity.setId(projectDTO.getId());
        projectEntity.setName(projectDTO.getName());
        projectEntity.setDescription(projectDTO.getDescription());
        projectEntity.setProjectStatus(projectStatusService.getByName(projectDTO.getProjectStatus()));
        projectEntity.setProjectCategory(projectCategoryService.getByName(projectDTO.getProjectCategory()));
        projectEntity.setContactInfo(projectDTO.getContactInfo());
        projectEntity.setLanguages(getLanguagesEntity(projectDTO.getLanguages()));

        return projectEntity;
    }

    @Override
    public VacancyEntity getVacancyEntity(VacancyDTO vacancyDTO) {

        VacancyEntity vacancyEntity=new VacancyEntity();

        vacancyEntity.setId(vacancyDTO.getId());
        vacancyEntity.setPersonNumber(vacancyDTO.getPersonNumber());
        vacancyEntity.setPosition(positionService.getByName(vacancyDTO.getPosition()));
        vacancyEntity.setRole(roleService.getByName(vacancyDTO.getRole()));
        vacancyEntity.setLanguages(getLanguagesEntity(vacancyDTO.getLanguages()));
        vacancyEntity.setSkills(getSkillsEntity(vacancyDTO.getSkills()));

        return vacancyEntity;
    }

    @Override
    public MessageRequestEntity getMessageRequestEntity(MessageRequestDTO messageRequestDTO) {

        MessageRequestEntity messageRequestEntity = new MessageRequestEntity();

        messageRequestEntity.setId(messageRequestDTO.getId());
        messageRequestEntity.setRequestType(requestTypeService.get(messageRequestDTO.getRequestType()));
        messageRequestEntity.setText(messageRequestDTO.getText());
        messageRequestEntity.setRead(messageRequestDTO.getRead());
        messageRequestEntity.setTitle(messageRequestDTO.getTitle());
        messageRequestEntity.setDate(messageRequestDTO.getDate());
        messageRequestEntity.setReceiverPerson(getPersonEntity(messageRequestDTO.getReceiverPerson()));
        messageRequestEntity.setSenderPerson(getPersonEntity(messageRequestDTO.getSenderPerson()));
        messageRequestEntity.setReceiverVisible(messageRequestDTO.getReceiverVisible());
        messageRequestEntity.setSenderVisible(messageRequestDTO.getSenderVisible());
        return messageRequestEntity;
    }

    @Override
    public PersonEntity getPersonEntity(ProfileDTO profileDTO)  {

        PersonEntity personEntity=new PersonEntity();

        personEntity.setId(profileDTO.getId());
        personEntity.setLogin(profileDTO.getLogin());
        personEntity.setPassword(profileDTO.getPassword());
        personEntity.setBlocked(profileDTO.getBlocked());
        personEntity.setRole(roleService.getByName(profileDTO.getRole()));

        ContactEntity contactEntity=getContactEntity(profileDTO.getContact());

        personEntity.setContact(contactEntity);
        contactEntity.setPerson(personEntity);

        ProfileEntity profileEntity=new ProfileEntity();
        profileEntity.setApproved(profileDTO.getApproved());
        profileEntity.setDirection(directionService.getByName(profileDTO.getDirection()));



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
