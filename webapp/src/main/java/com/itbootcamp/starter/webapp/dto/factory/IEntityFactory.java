package com.itbootcamp.starter.webapp.dto.factory;


import com.itbootcamp.starter.datamodel.impl.ReviewEntity;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import com.itbootcamp.starter.datamodel.impl.*;
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
    List<SkillEntity> getSkillsEntity(List<SkillDTO> skillsDTO);


}
