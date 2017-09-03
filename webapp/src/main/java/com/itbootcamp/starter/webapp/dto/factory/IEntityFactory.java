package com.itbootcamp.starter.webapp.dto.factory;


import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import com.itbootcamp.starter.webapp.dto.*;


import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
public interface IEntityFactory {

    ReviewEntity getReviewEntity(ReviewDTO reviewDTO);


    PersonEntity getPersonEntity(ProfileDTO profileDTO);
    EducationEntity getEducationEntity(EducationDTO educationDTO);
    CourseEntity getCourseEntity(CourseDTO courseDTO);
    WorkplaceEntity getWorkplaceEntity(WorkplaceDTO workplaceDTO);
    List<SkillEntity> getSkillsEntity(List<String> skillsDTO);
    List<LanguageEntity> getLanguagesEntity(List<String> languages);
    LocationEntity getLocationEntity(LocationDTO locationDTO);
    ContactEntity getContactEntity(ContactDTO contactDTO);
    ProjectEntity getProjectEntity(ProjectDTO projectDTO);
    VacancyEntity getVacancyEntity(VacancyDTO vacancyDTO);



}
