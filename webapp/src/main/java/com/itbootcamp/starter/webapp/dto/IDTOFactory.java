package com.itbootcamp.starter.webapp.dto;

import com.itbootcamp.starter.datamodel.impl.*;

public interface IDTOFactory {

    public ProjectDTO getProjectDTO(ProjectEntity projectEntity);
    public VacancyDTO getVacancyDTO(VacancyEntity vacancyEntity);
    public LanguageDTO getLanguageDTO(LanguageEntity languageEntity);
    public RoleDTO getRoleDTO(RoleEntity roleEntity);
    public PositionDTO getPositionDTO(PositionEntity positionEntity);
    public MemberDTO getMemberDTO(PersonEntity personEntity,ProjectEntity projectEntity);
    public SkillDTO getSkillDTO(SkillEntity skillEntity);
    public CustomerAdminModerDTO getCustomerAdminModerDTO(PersonEntity personEntity);
    public MentorDTO getMentorDTO(PersonEntity personEntity);
    public TraineeDTO getTraineeDTO(PersonEntity personEntity);
    public DirectionDTO getDirectionDTO(DirectionEntity directionEntity);
    public CourseDTO getCourseDTO(CourseEntity courseEntity);
    public WorkplaceDTO getWorkplaceDTO(WorkplaceEntity workplaceEntity);
    public EducationDTO getEducationDTO(EducationEntity educationEntity);
}
