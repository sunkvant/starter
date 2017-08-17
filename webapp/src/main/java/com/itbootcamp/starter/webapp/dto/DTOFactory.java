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

        projectDTO.setCustomerId(projectEntity.getCustomer().getId());
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

        memberDTO.setId(personEntity.getId());
        memberDTO.setFullName(personEntity.getContact().getFullName());
        memberDTO.setPosition(getPositionDTO(personService.getPositionOnProjectByPersonIdAndByProjectId(personEntity.getId(),projectEntity.getId())));
        memberDTO.setRole(getRoleDTO(personEntity.getRole()));
        memberDTO.setMember(personService.getStatusOnProjectByPersonIdAndByProjectId(personEntity.getId(),projectEntity.getId()));


        return memberDTO;
    }

    @Override
    public SkillDTO getSkillDTO(SkillEntity skillEntity) {
        SkillDTO skillDTO=new SkillDTO();

        skillDTO.setId(skillEntity.getId());
        skillDTO.setName(skillEntity.getName());

        return skillDTO;
    }


}
