package com.itbootcamp.starter.webapp.dto;

import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.services.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Scope(value = "singleton")
public class DTOFactory implements  IDTOFactory {


    @Autowired
    private PersonService personService;

    @Override
    public ProjectDTO getProjectDTO(ProjectEntity projectEntity) {

        ProjectDTO projectDTO=new ProjectDTO();

        projectDTO.setId(projectEntity.getId());
        projectDTO.setCustomer(getProfileDTO(projectEntity.getCustomer()));
        projectDTO.setName(projectEntity.getName());
        projectDTO.setDescription(projectEntity.getDescription());
        projectDTO.setDateStart(projectEntity.getDateStart());
        projectDTO.setDateEnd(projectEntity.getDateEnd());
        projectDTO.setContactInfo(projectEntity.getContactInfo());
        projectDTO.setProjectStatus(projectEntity.getProjectStatus().getStatus());
        projectDTO.setProjectCategory(projectEntity.getProjectCategory().getCategory());


        List<LanguageDTO> languages=new ArrayList<>();
        for(int i=0; i<projectEntity.getLanguages().size(); i++) {
            languages.add(getLanguageDTO(projectEntity.getLanguages().get(i)));
        }

        projectDTO.setLanguages(languages);

        List<VacancyDTO> vacanciesDTO=new ArrayList<>();

        for (int i = 0; i < projectEntity.getVacancies().size(); i++) {

            vacanciesDTO.add(getVacancyDTO(projectEntity.getVacancies().get(i)));

        }

        projectDTO.setVacancies(vacanciesDTO);

        List<MemberDTO> membersDTO=new ArrayList<>();

        for (int i = 0; i < personService.getAllPersonsByProjectId(projectEntity.getId()).size(); i++) {


            membersDTO.add(getMemberDTO(personService.getAllPersonsByProjectId(projectEntity.getId()).get(i),projectEntity));

        }


        projectDTO.setTeam(membersDTO);

        return projectDTO;
    }

    @Override
    public VacancyDTO getVacancyDTO(VacancyEntity vacancyEntity) {

        VacancyDTO vacancyDTO=new VacancyDTO();

        vacancyDTO.setId(vacancyEntity.getId());
        vacancyDTO.setPersonNumber(vacancyEntity.getPersonNumber());
        vacancyDTO.setPosition(getPositionDTO(vacancyEntity.getPosition()));
        vacancyDTO.setRole(getRoleDTO(vacancyEntity.getRole()));

        List<SkillDTO> skillsDTO=new ArrayList<>();

        for (int i = 0; i < vacancyEntity.getSkills().size(); i++) {

            skillsDTO.add(getSkillDTO(vacancyEntity.getSkills().get(i)));

        }

        vacancyDTO.setSkills(skillsDTO);


        return vacancyDTO;
    }

    @Override
    public LanguageDTO getLanguageDTO(LanguageEntity languageEntity) {

        LanguageDTO languageDTO =new LanguageDTO();

        languageDTO.setId(languageEntity.getId());
        languageDTO.setName(languageEntity.getName());

        return languageDTO;
    }

    @Override
    public RoleDTO getRoleDTO(RoleEntity roleEntity) {

        RoleDTO roleDTO=new RoleDTO();

        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());

        return roleDTO;
    }

    @Override
    public PositionDTO getPositionDTO(PositionEntity positionEntity) {
        PositionDTO positionDTO=new PositionDTO();

        positionDTO.setId(positionEntity.getId());
        positionDTO.setName(positionEntity.getName());

        return positionDTO;
    }

    @Override
    public MemberDTO getMemberDTO(PersonEntity personEntity,ProjectEntity projectEntity) {


        MemberDTO memberDTO=new MemberDTO();

        memberDTO.setMember(getProfileDTO(personEntity));
        memberDTO.setPosition(getPositionDTO(personService.getPositionOnProjectByPersonIdAndByProjectId(personEntity.getId(),projectEntity.getId())));
        memberDTO.setRole(getRoleDTO(personEntity.getRole()));
        memberDTO.setActive(personService.getStatusOnProjectByPersonIdAndByProjectId(personEntity.getId(),projectEntity.getId()));


        return memberDTO;
    }

    @Override
    public SkillDTO getSkillDTO(SkillEntity skillEntity) {
        SkillDTO skillDTO=new SkillDTO();

        skillDTO.setId(skillEntity.getId());
        skillDTO.setName(skillEntity.getName());

        return skillDTO;
    }


    @Override
    public ProfileDTO getProfileDTO(PersonEntity personEntity) {

        ProfileDTO profileDTO =new ProfileDTO();

        profileDTO.setId(personEntity.getId());
        profileDTO.setLogin(personEntity.getLogin());
        profileDTO.setFullName(personEntity.getContact().getFullName());
        profileDTO.setDateOfBirth(personEntity.getContact().getDateOfBirth());
        profileDTO.setAvatarPath(personEntity.getContact().getAvatarPath());
        profileDTO.setPhone(personEntity.getContact().getPhone());
        profileDTO.setSkype(personEntity.getContact().getSkype());
        profileDTO.setEmail(personEntity.getContact().getEmail());
        profileDTO.setAbout(personEntity.getContact().getAbout());
        profileDTO.setRole(getRoleDTO(personEntity.getRole()));
        profileDTO.setBlocked(personEntity.getBlocked());
        profileDTO.setApproved(personEntity.getProfile().getApproved());

        profileDTO.setDirection(getDirectionDTO(personEntity.getProfile().getDirection()));


        List<CourseDTO> courseDTO=new ArrayList<>();

        for (int i = 0; i < personEntity.getProfile().getCourses().size(); i++) {

            courseDTO.add(getCourseDTO(personEntity.getProfile().getCourses().get(i)));

        }

        profileDTO.setCourses(courseDTO);

        List<WorkplaceDTO> workplacesDTO=new ArrayList<>();

        for (int i = 0; i < personEntity.getProfile().getWorkplaces().size(); i++) {

            workplacesDTO.add(getWorkplaceDTO(personEntity.getProfile().getWorkplaces().get(i)));

        }

        profileDTO.setWorkplaces(workplacesDTO);


        List<EducationDTO> educationsDTO=new ArrayList<>();

        for (int i = 0; i < personEntity.getProfile().getEducations().size(); i++) {

            educationsDTO.add(getEducationDTO(personEntity.getProfile().getEducations().get(i)));

        }

        profileDTO.setEducations(educationsDTO);

        List<SkillDTO> skillsDTO=new ArrayList<>();

        for (int i = 0; i < personEntity.getProfile().getSkills().size(); i++) {

            skillsDTO.add(getSkillDTO(personEntity.getProfile().getSkills().get(i)));

        }

        profileDTO.setSkills(skillsDTO);



        if (personEntity.getMentorInfo()==null) {

            profileDTO.setMentorExp(false);
            profileDTO.setExperience("");

        } else {

            profileDTO.setMentorExp(personEntity.getMentorInfo().getMentorExp());
            profileDTO.setExperience(personEntity.getMentorInfo().getExperience());

        }



        return profileDTO;

    }

    @Override
    public DirectionDTO getDirectionDTO(DirectionEntity directionEntity) {

        DirectionDTO directionDTO=new DirectionDTO();

        directionDTO.setId(directionEntity.getId());
        directionDTO.setName(directionEntity.getName());

        return directionDTO;
    }

    @Override
    public CourseDTO getCourseDTO(CourseEntity courseEntity) {

        CourseDTO courseDTO=new CourseDTO();

        courseDTO.setId(courseEntity.getId());
        courseDTO.setName(courseEntity.getName());
        courseDTO.setOrganization(courseEntity.getOrganization());
        courseDTO.setSpeciality(courseEntity.getSpeciality());
        courseDTO.setGraduationYear(courseEntity.getGraduationYear());

        return courseDTO;
    }

    @Override
    public WorkplaceDTO getWorkplaceDTO(WorkplaceEntity workplaceEntity) {

        WorkplaceDTO workplaceDTO=new WorkplaceDTO();

        workplaceDTO.setId(workplaceEntity.getId());
        workplaceDTO.setCompany(workplaceEntity.getCompany());
        workplaceDTO.setSphereOfActivity(workplaceEntity.getSphereOfActivity());
        workplaceDTO.setPosition(workplaceEntity.getPosition());
        workplaceDTO.setDuties(workplaceEntity.getDuties());
        workplaceDTO.setStartWork(workplaceEntity.getStartWork());
        workplaceDTO.setEndWork(workplaceEntity.getEndWork());
        workplaceDTO.setWorking(workplaceEntity.getWorking());

        return workplaceDTO;
    }

    @Override
    public EducationDTO getEducationDTO(EducationEntity educationEntity) {

        EducationDTO educationDTO=new EducationDTO();

        educationDTO.setId(educationEntity.getId());
        educationDTO.setName(educationEntity.getName());
        educationDTO.setFaculty(educationEntity.getFaculty());
        educationDTO.setSpeciality(educationEntity.getSpeciality());
        educationDTO.setGraduationYear(educationEntity.getGraduationYear());

        return educationDTO;
    }


}
