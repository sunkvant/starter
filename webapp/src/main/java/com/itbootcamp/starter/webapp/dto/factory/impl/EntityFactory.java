package com.itbootcamp.starter.webapp.dto.factory.impl;

import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.repository.ContactRepository;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.repository.SkillRepository;
import com.itbootcamp.starter.services.*;
import com.itbootcamp.starter.webapp.dto.*;
import com.itbootcamp.starter.services.SearchProjectEntity;
import com.itbootcamp.starter.webapp.dto.factory.IEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
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
    private EntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactRepository contactRepository;

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

    public List<EducationEntity> getEducationsEntity(List<EducationDTO> educationsDTO) {


        if (educationsDTO==null) {

            return null;


        }

        List<EducationEntity> educationEntities=new ArrayList<>();

            for(int i=0; i<educationsDTO.size(); i++) {

                EducationEntity educationEntity=new EducationEntity();

                educationEntity.setId(educationsDTO.get(i).getId());
                educationEntity.setName(educationsDTO.get(i).getName());
                educationEntity.setFaculty(educationsDTO.get(i).getFaculty());
                educationEntity.setSpeciality(educationsDTO.get(i).getSpeciality());
                educationEntity.setGraduationYear(educationsDTO.get(i).getGraduationYear());


                educationEntity.setEducationTypeEntity(getEducationTypeEntity(educationsDTO.get(i).getEducationType()));
                educationEntity.setProfile(null);

                educationEntities.add(educationEntity);


        }

return null;
    }


    @Override
    public PersonEntity getPersonEntity(ProfileDTO profileDTO)  {

        PersonEntity personEntity=new PersonEntity();

        personEntity.setId(profileDTO.getId());
        personEntity.setLogin(profileDTO.getLogin());
        personEntity.setPassword(personEntity.getPassword());
        personEntity.setBlocked(profileDTO.getBlocked());
        personEntity.setRole(getRoleEntity(profileDTO.getRole()));

        ContactEntity contactEntity=new ContactEntity();
        contactEntity.setFullName(profileDTO.getFullName());
        contactEntity.setDateOfBirth(profileDTO.getDateOfBirth());
        contactEntity.setAvatarPath(profileDTO.getAvatarPath());
        contactEntity.setPhone(profileDTO.getPhone());
        contactEntity.setSkype(profileDTO.getSkype());
        contactEntity.setEmail(profileDTO.getEmail());
        contactEntity.setAbout(profileDTO.getAbout());

        personEntity.setContact(contactEntity);
        contactEntity.setPerson(personEntity);

        ProfileEntity profileEntity=new ProfileEntity();
        profileEntity.setApproved(profileDTO.getApproved());
        profileEntity.setDirection(getDirectionEntity(profileDTO.getDirection()));

        List<EducationEntity> educationEntities=new ArrayList<>();

        if (profileDTO.getEducations()!=null) {

            for(int i=0; i<profileDTO.getEducations().size(); i++) {

                EducationEntity educationEntity=new EducationEntity();

                educationEntity.setId(profileDTO.getEducations().get(i).getId());
                educationEntity.setName(profileDTO.getEducations().get(i).getName());
                educationEntity.setFaculty(profileDTO.getEducations().get(i).getFaculty());
                educationEntity.setSpeciality(profileDTO.getEducations().get(i).getSpeciality());
                educationEntity.setGraduationYear(profileDTO.getEducations().get(i).getGraduationYear());

                EducationTypeEntity educationTypeEntity=new EducationTypeEntity();

                educationTypeEntity.setId(profileDTO.getEducations().get(i).getEducationType().getId());
                educationTypeEntity.setType(profileDTO.getEducations().get(i).getEducationType().getName());

                educationEntity.setEducationTypeEntity(educationTypeEntity);
                educationEntity.setProfile(profileEntity);

                educationEntities.add(educationEntity);



            }

        }

        profileEntity.setEducations(educationEntities);

        List<CourseEntity> courseEntities=new ArrayList<>();

        if (profileDTO.getCourses()!=null) {

            for(int i=0; i<profileDTO.getCourses().size(); i++) {

                CourseEntity courseEntity=new CourseEntity();

                courseEntity.setId(profileDTO.getCourses().get(i).getId());
                courseEntity.setName(profileDTO.getCourses().get(i).getName());
                courseEntity.setOrganization(profileDTO.getCourses().get(i).getOrganization());
                courseEntity.setSpeciality(profileDTO.getCourses().get(i).getSpeciality());
                courseEntity.setGraduationYear(profileDTO.getCourses().get(i).getGraduationYear());
                courseEntity.setProfile(profileEntity);

                courseEntities.add(courseEntity);

            }

        }

        profileEntity.setCourses(courseEntities);


        List<WorkplaceEntity> workplaceEntities=new ArrayList<>();

        if (profileDTO.getWorkplaces()!=null) {

            for(int i=0; i<profileDTO.getWorkplaces().size(); i++) {

                WorkplaceEntity workplaceEntity=new WorkplaceEntity();

                workplaceEntity.setId(profileDTO.getWorkplaces().get(i).getId());
                workplaceEntity.setCompany(profileDTO.getWorkplaces().get(i).getCompany());
                workplaceEntity.setSphereOfActivity(profileDTO.getWorkplaces().get(i).getSphereOfActivity());
                workplaceEntity.setPosition(profileDTO.getWorkplaces().get(i).getPosition());
                workplaceEntity.setDuties(profileDTO.getWorkplaces().get(i).getDuties());
                workplaceEntity.setStartWork(profileDTO.getWorkplaces().get(i).getStartWork());
                workplaceEntity.setEndWork(profileDTO.getWorkplaces().get(i).getEndWork());
                workplaceEntity.setWorking(profileDTO.getWorkplaces().get(i).getWorking());

                workplaceEntities.add(workplaceEntity);

            }

        }

        profileEntity.setWorkplaces(workplaceEntities);

        List<SkillEntity> skillEntities=new ArrayList<>();

        if (profileDTO.getSkills()!=null) {


            for(int i=0; i<profileDTO.getSkills().size(); i++) {

                SkillEntity skillEntity=new SkillEntity();

                skillEntity.setId(profileDTO.getSkills().get(i).getId());
                skillEntity.setName(profileDTO.getSkills().get(i).getName());

                skillEntities.add(skillEntity);

            }

        }

        profileEntity.setSkills(skillEntities);

        profileEntity.setPerson(personEntity);

        personEntity.setProfile(profileEntity);

        return personEntity;
    }

}
