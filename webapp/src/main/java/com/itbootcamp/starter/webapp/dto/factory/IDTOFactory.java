package com.itbootcamp.starter.webapp.dto.factory;

import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.webapp.dto.*;

import java.util.List;

public interface IDTOFactory {

    ProjectDTO getProjectDTO(ProjectEntity projectEntity);
    VacancyDTO getVacancyDTO(VacancyEntity vacancyEntity);
    MemberDTO getMemberDTO(PersonEntity personEntity,ProjectEntity projectEntity);
    ProfileDTO getProfileDTO(PersonEntity personEntity);
    CourseDTO getCourseDTO(CourseEntity courseEntity);
    WorkplaceDTO getWorkplaceDTO(WorkplaceEntity workplaceEntity);
    EducationDTO getEducationDTO(EducationEntity educationEntity);
    ReviewDTO getReviewDTO(ReviewEntity reviewEntity);
    LocationDTO getLocationDTO(LocationEntity locationEntity);
    ContactDTO getContactDTO(ContactEntity contactEntity);
    List<String> getLanguages(List<LanguageEntity> languagesEntity);
    List<String> getSkills(List<SkillEntity> skillsEntity);
    List<String> getCities(List<CityEntity> citiesEntity);
    List<String> getCountries(List<CountryEntity> counriesEntity);
    List<String> getDirections(List<DirectionEntity> directionsEntity);
    MessageRequestDTO getMessageRequestDTO(MessageRequestEntity messageRequestEntity);
    VacancyRequestDTO getVacancyRequestDTO(VacancyRequestEntity vacancyRequestEntity);
    ConsultationRequestDTO getConsultationRequestDTO(ConsultationRequestEntity consultationRequestEntity);

    AssessmentRequestDTO getAssessmentRequestDTO(AssessmentRequestEntity assessmentRequestEntity);

    AbstractRequestDTO getRequestDTO(RequestEntity requestEntity);
}
