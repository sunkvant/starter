package com.itbootcamp.starter.webapp.dto.factory;

import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.webapp.dto.*;

import java.util.List;

public interface IDTOFactory {

    ProjectDTO getProjectDTO(ProjectEntity projectEntity);
    VacancyDTO getVacancyDTO(VacancyEntity vacancyEntity);
    LanguageDTO getLanguageDTO(LanguageEntity languageEntity);
    RoleDTO getRoleDTO(RoleEntity roleEntity);
    PositionDTO getPositionDTO(PositionEntity positionEntity);
    MemberDTO getMemberDTO(PersonEntity personEntity,ProjectEntity projectEntity);
    SkillDTO getSkillDTO(SkillEntity skillEntity);
    ProfileDTO getProfileDTO(PersonEntity personEntity);
    DirectionDTO getDirectionDTO(DirectionEntity directionEntity);
    CourseDTO getCourseDTO(CourseEntity courseEntity);
    List<CourseDTO> getCoursesDTO(List<CourseEntity> coursesEntity);
    WorkplaceDTO getWorkplaceDTO(WorkplaceEntity workplaceEntity);
    EducationDTO getEducationDTO(EducationEntity educationEntity);
    ReviewDTO getReviewDTO(ReviewEntity reviewEntity);
}
