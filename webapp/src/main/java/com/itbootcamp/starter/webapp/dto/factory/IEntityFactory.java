package com.itbootcamp.starter.webapp.dto.factory;

import com.itbootcamp.starter.datamodel.impl.ReviewEntity;
import com.itbootcamp.starter.webapp.dto.ReviewDTO;
import com.itbootcamp.starter.webapp.dto.SearchProjectDTO;
import com.itbootcamp.starter.services.SearchProjectEntity;

/**
 * Created by admin on 8/21/2017.
 */
public interface IEntityFactory {

    ReviewEntity getReviewEntity(ReviewDTO reviewDTO);

    SearchProjectEntity getSearchProjectEntity(SearchProjectDTO searchProjectDTO);
}
