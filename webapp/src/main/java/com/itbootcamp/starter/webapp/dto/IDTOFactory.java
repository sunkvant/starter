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

}
