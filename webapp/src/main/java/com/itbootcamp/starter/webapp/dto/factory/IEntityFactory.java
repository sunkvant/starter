package com.itbootcamp.starter.webapp.dto.factory;

import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.webapp.dto.*;
import com.itbootcamp.starter.services.SearchProjectEntity;

import java.util.List;

/**
 * Created by admin on 8/21/2017.
 */
public interface IEntityFactory {

    ReviewEntity getReviewEntity(ReviewDTO reviewDTO);
    SearchProjectEntity getSearchProjectEntity(SearchProjectDTO searchProjectDTO);
    PersonEntity getPersonEntity(ProfileDTO profileDTO);
    EducationEntity getEducationEntity(EducationDTO educationDTO);
    CourseEntity getCourseEntity(CourseDTO courseDTO);
    WorkplaceEntity getWorkplaceEntity(WorkplaceDTO workplaceDTO);
    List<SkillEntity> getSkillsEntity(List<SkillDTO> skillsDTO);


}
