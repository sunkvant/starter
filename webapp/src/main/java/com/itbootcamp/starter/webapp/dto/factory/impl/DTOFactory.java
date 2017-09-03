package com.itbootcamp.starter.webapp.dto.factory.impl;

import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.*;
import com.itbootcamp.starter.webapp.dto.factory.IDTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Scope(value = "singleton")
public class DTOFactory implements IDTOFactory {


    @Autowired
    private PersonService personService;


    @Override
    public ProjectDTO getProjectDTO(ProjectEntity projectEntity) {

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(projectEntity.getId());
        projectDTO.setCustomer(getProfileDTO(projectEntity.getCustomer()));
        projectDTO.setName(projectEntity.getName());
        projectDTO.setDescription(projectEntity.getDescription());
        projectDTO.setDateStart(projectEntity.getDateStart());
        projectDTO.setDateEnd(projectEntity.getDateEnd());
        projectDTO.setContactInfo(projectEntity.getContactInfo());
        projectDTO.setProjectStatus(projectEntity.getProjectStatus().getStatus());
        projectDTO.setProjectCategory(projectEntity.getProjectCategory().getCategory());


        List<String> languages = new ArrayList<>();

        for (int i = 0; i < projectEntity.getLanguages().size(); i++) {
            languages.add(projectEntity.getLanguages().get(i).getName());
        }

        projectDTO.setLanguages(languages);

        List<VacancyDTO> vacanciesDTO = new ArrayList<>();

        for (int i = 0; i < projectEntity.getVacancies().size(); i++) {

            vacanciesDTO.add(getVacancyDTO(projectEntity.getVacancies().get(i)));

        }

        projectDTO.setVacancies(vacanciesDTO);

        List<MemberDTO> membersDTO = new ArrayList<>();

        for (int i = 0; i < personService.getAllPersonsByProjectId(projectEntity.getId()).size(); i++) {


            membersDTO.add(getMemberDTO(personService.getAllPersonsByProjectId(projectEntity.getId()).get(i), projectEntity));

        }

        projectDTO.setTeam(membersDTO);


        List<ReviewDTO> reviewsDTO = new ArrayList<>();

        for (int i = 0; i < projectEntity.getReviews().size(); i++) {

            reviewsDTO.add(getReviewDTO(projectEntity.getReviews().get(i)));

        }

        projectDTO.setReviews(reviewsDTO);


        return projectDTO;
    }

    @Override
    public VacancyDTO getVacancyDTO(VacancyEntity vacancyEntity) {

        VacancyDTO vacancyDTO = new VacancyDTO();

        vacancyDTO.setId(vacancyEntity.getId());
        vacancyDTO.setProjectId(vacancyEntity.getProject().getId());
        vacancyDTO.setPersonNumber(vacancyEntity.getPersonNumber());
        vacancyDTO.setPosition(vacancyEntity.getPosition().getName());
        vacancyDTO.setRole(vacancyEntity.getRole().getName());


        List<String> skills = new ArrayList<>();

        for (int i = 0; i < vacancyEntity.getSkills().size(); i++) {

            skills.add(vacancyEntity.getSkills().get(i).getName());

        }
        vacancyDTO.setSkills(skills);

        List<String> languages = new ArrayList<>();


        for (int i = 0; i < vacancyEntity.getLanguages().size(); i++) {

            languages.add(vacancyEntity.getLanguages().get(i).getName());
        }
        vacancyDTO.setLanguages(languages);

        return vacancyDTO;
    }


    @Override
    public MemberDTO getMemberDTO(PersonEntity personEntity, ProjectEntity projectEntity) {

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setProfile(getProfileDTO(personEntity));
        memberDTO.setPosition(personService.getPositionOnProjectByPersonIdAndByProjectId(personEntity.getId(), projectEntity.getId()).getName());
        memberDTO.setRole(personEntity.getRole().getName());
        memberDTO.setActive(personService.getStatusOnProjectByPersonIdAndByProjectId(personEntity.getId(), projectEntity.getId()));


        return memberDTO;
    }


    @Override
    public ProfileDTO getProfileDTO(PersonEntity personEntity) {

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setId(personEntity.getId());
        profileDTO.setLogin(personEntity.getLogin());
        profileDTO.setPassword(personEntity.getPassword());
        profileDTO.setContact(getContactDTO(personEntity.getContact()));

        profileDTO.setRole(personEntity.getRole().getName());
        profileDTO.setBlocked(personEntity.getBlocked());


        if ((personEntity.getRole().getName().equals(RoleType.ROLE_TRAINEE)) || (personEntity.getRole().getName().equals(RoleType.ROLE_MENTOR))) {


            profileDTO.setApproved(personEntity.getProfile().getApproved());

            profileDTO.setDirection(personEntity.getProfile().getDirection().getName());


            List<CourseDTO> coursesDTO = new ArrayList<>();

            for (int i = 0; i < personEntity.getProfile().getCourses().size(); i++) {

                coursesDTO.add(getCourseDTO(personEntity.getProfile().getCourses().get(i)));

            }

            profileDTO.setCourses(coursesDTO);

            List<WorkplaceDTO> workplacesDTO = new ArrayList<>();

            for (int i = 0; i < personEntity.getProfile().getWorkplaces().size(); i++) {

                workplacesDTO.add(getWorkplaceDTO(personEntity.getProfile().getWorkplaces().get(i)));

            }

            profileDTO.setWorkplaces(workplacesDTO);


            List<EducationDTO> educationsDTO = new ArrayList<>();

            for (int i = 0; i < personEntity.getProfile().getEducations().size(); i++) {

                educationsDTO.add(getEducationDTO(personEntity.getProfile().getEducations().get(i)));

            }

            profileDTO.setEducations(educationsDTO);

            List<String> skills = new ArrayList<>();

            for (int i = 0; i < personEntity.getProfile().getSkills().size(); i++) {

                skills.add(personEntity.getProfile().getSkills().get(i).getName());

            }

            profileDTO.setSkills(skills);


            if ((personEntity.getRole().getName().equals(RoleType.ROLE_MENTOR))) {

                profileDTO.setMentorExp(personEntity.getProfile().getMentorInfo().getMentorExp());
                profileDTO.setExperience(personEntity.getProfile().getMentorInfo().getExperience());

            }

        }

        return profileDTO;

    }


    @Override
    public CourseDTO getCourseDTO(CourseEntity courseEntity) {

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId(courseEntity.getId());
        courseDTO.setName(courseEntity.getName());
        courseDTO.setOrganization(courseEntity.getOrganization());
        courseDTO.setSpeciality(courseEntity.getSpeciality());
        courseDTO.setGraduationYear(courseEntity.getGraduationYear());

        return courseDTO;
    }

    @Override
    public WorkplaceDTO getWorkplaceDTO(WorkplaceEntity workplaceEntity) {

        WorkplaceDTO workplaceDTO = new WorkplaceDTO();

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

        EducationDTO educationDTO = new EducationDTO();

        educationDTO.setId(educationEntity.getId());
        educationDTO.setName(educationEntity.getName());
        educationDTO.setFaculty(educationEntity.getFaculty());
        educationDTO.setSpeciality(educationEntity.getSpeciality());
        educationDTO.setGraduationYear(educationEntity.getGraduationYear());
        educationDTO.setEducationType(educationEntity.getEducationType());

        return educationDTO;
    }

    @Override
    public ReviewDTO getReviewDTO(ReviewEntity reviewEntity) {

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setId(reviewEntity.getId());
        reviewDTO.setProjectId(reviewEntity.getProject().getId());
        reviewDTO.setDate(reviewEntity.getDate());
        reviewDTO.setRating(reviewEntity.getRating());
        reviewDTO.setReceiverPersonId(reviewEntity.getReceiverPerson().getId());
        reviewDTO.setSenderPersonId(reviewEntity.getSenderPerson().getId());
        reviewDTO.setText(reviewEntity.getText());

        return reviewDTO;
    }


    @Override
    public LocationDTO getLocationDTO(LocationEntity locationEntity) {

        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setCity(locationEntity.getCity().getName());
        locationDTO.setCountry(locationEntity.getCountry().getName());

        return locationDTO;
    }


    @Override
    public ContactDTO getContactDTO(ContactEntity contactEntity) {

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setFullName(contactEntity.getFullName());
        contactDTO.setDateOfBirth(contactEntity.getDateOfBirth());
        contactDTO.setAvatarPath(contactEntity.getAvatarPath());
        contactDTO.setPhone(contactEntity.getPhone());
        contactDTO.setSkype(contactEntity.getSkype());
        contactDTO.setEmail(contactEntity.getEmail());
        contactDTO.setAbout(contactEntity.getAbout());
        contactDTO.setLocation(getLocationDTO(contactEntity.getLocation()));

        return contactDTO;
    }

    @Override
    public List<String> getLanguages(List<LanguageEntity> languagesEntity) {

        List<String> languages = new ArrayList<>();

        for (int i = 0; i < languagesEntity.size(); i++) {

            languages.add(languagesEntity.get(i).getName());

        }


        return languages;
    }

    @Override
    public List<String> getSkills(List<SkillEntity> skillsEntity) {
        List<String> skills = new ArrayList<>();

        for (int i = 0; i < skillsEntity.size(); i++) {

            skills.add(skillsEntity.get(i).getName());

        }


        return skills;
    }

    @Override
    public List<String> getCities(List<CityEntity> citiesEntity) {
        List<String> cities = new ArrayList<>();

        for (int i = 0; i < citiesEntity.size(); i++) {

            cities.add(citiesEntity.get(i).getName());

        }

        return cities;
    }

    @Override
    public List<String> getCountries(List<CountryEntity> countriesEntity) {

        List<String> countries = new ArrayList<>();

        for (int i = 0; i < countriesEntity.size(); i++) {

            countries.add(countriesEntity.get(i).getName());

        }


        return countries;
    }

    @Override
    public List<String> getDirections(List<DirectionEntity> directionsEntity) {

        List<String> directions = new ArrayList<>();

        for (int i = 0; i < directionsEntity.size(); i++) {

            directions.add(directionsEntity.get(i).getName());

        }


        return directions;
    }

    @Override
    public RequestDTO getMessageRequestDTO(MessageRequestEntity messageRequestEntity) {
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setId(messageRequestEntity.getId());
        requestDTO.setText(messageRequestEntity.getText());
        requestDTO.setTitle(messageRequestEntity.getTitle());
        requestDTO.setDate(messageRequestEntity.getDate());
        requestDTO.setRead(messageRequestEntity.getRead());
        requestDTO.setReceiverPerson(getProfileDTO(messageRequestEntity.getReceiverPerson()));
        requestDTO.setSenderPerson(getProfileDTO(messageRequestEntity.getSenderPerson()));
        requestDTO.setRequestType(messageRequestEntity.getRequestType().getType());
        requestDTO.setReceiverVisible(messageRequestEntity.getReceiverVisible());
        requestDTO.setSenderVisible(messageRequestEntity.getSenderVisible());

        return requestDTO;
    }

    @Override
    public RequestDTO getVacancyRequestDTO(VacancyRequestEntity vacancyRequestEntity) {
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setId(vacancyRequestEntity.getId());
        requestDTO.setAnswered(vacancyRequestEntity.getAnswered());
        requestDTO.setVacancyDTO(getVacancyDTO(vacancyRequestEntity.getVacancy()));
        requestDTO.setDate(vacancyRequestEntity.getDate());
        requestDTO.setRead(vacancyRequestEntity.getRead());
        requestDTO.setReceiverPerson(getProfileDTO(vacancyRequestEntity.getReceiverPerson()));
        requestDTO.setSenderPerson(getProfileDTO(vacancyRequestEntity.getSenderPerson()));
        requestDTO.setRequestType(vacancyRequestEntity.getRequestType().getType());
        requestDTO.setReceiverVisible(vacancyRequestEntity.getReceiverVisible());
        requestDTO.setSenderVisible(vacancyRequestEntity.getSenderVisible());

        return requestDTO;
    }

    @Override
    public RequestDTO getConsultationRequestDTO(ConsultationRequestEntity consultationRequestEntity) {
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setId(consultationRequestEntity.getId());
        requestDTO.setSingle(consultationRequestEntity.getSingle());
        requestDTO.setProjectDTO(getProjectDTO(consultationRequestEntity.getProject()));
        requestDTO.setTitle(consultationRequestEntity.getTitle());
        requestDTO.setDate(consultationRequestEntity.getDate());
        requestDTO.setRead(consultationRequestEntity.getRead());
        requestDTO.setReceiverPerson(getProfileDTO(consultationRequestEntity.getReceiverPerson()));
        requestDTO.setSenderPerson(getProfileDTO(consultationRequestEntity.getSenderPerson()));
        requestDTO.setRequestType(consultationRequestEntity.getRequestType().getType());
        requestDTO.setReceiverVisible(consultationRequestEntity.getReceiverVisible());
        requestDTO.setSenderVisible(consultationRequestEntity.getSenderVisible());

        return requestDTO;
    }

    @Override
    public RequestDTO getAssessmentRequestDTO(AssessmentRequestEntity assessmentRequestEntity) {
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setId(assessmentRequestEntity.getId());
        requestDTO.setAnswered(assessmentRequestEntity.getAnswered());
        requestDTO.setDate(assessmentRequestEntity.getDate());
        requestDTO.setRead(assessmentRequestEntity.getRead());
        requestDTO.setReceiverPerson(getProfileDTO(assessmentRequestEntity.getReceiverPerson()));
        requestDTO.setSenderPerson(getProfileDTO(assessmentRequestEntity.getSenderPerson()));
        requestDTO.setRequestType(assessmentRequestEntity.getRequestType().getType());
        requestDTO.setReceiverVisible(assessmentRequestEntity.getReceiverVisible());
        requestDTO.setSenderVisible(assessmentRequestEntity.getSenderVisible());
        return requestDTO;
    }

    @Override
    public RequestDTO getRequestDTO(RequestEntity requestEntity) {
        RequestDTO requestDTO = new RequestDTO();

        switch (requestEntity.getRequestType().getType()){
            case RequestType.REQUEST_MESSAGE: requestDTO = getMessageRequestDTO((MessageRequestEntity) requestEntity); break;
            case RequestType.REQUEST_VACANCY: requestDTO = getVacancyRequestDTO((VacancyRequestEntity) requestEntity); break;
            case RequestType.REQUEST_CONSULTATION: requestDTO = getConsultationRequestDTO((ConsultationRequestEntity) requestEntity); break;
            case RequestType.REQUEST_ASSESSMENT: requestDTO = getAssessmentRequestDTO((AssessmentRequestEntity) requestEntity); break;
        }

        return requestDTO;
    }
}
