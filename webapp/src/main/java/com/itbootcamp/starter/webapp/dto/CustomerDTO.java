package com.itbootcamp.starter.webapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itbootcamp.starter.datamodel.impl.ProjectEntity;

import java.util.List;

/**
 * Created by admin on 8/15/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends AbstractPersonDTO {



    private List<ProjectEntity> customerProjects;

    public List<ProjectEntity> getCustomerProjects() {
        return customerProjects;
    }

    public void setCustomerProjects(List<ProjectEntity> customerProjects) {
        this.customerProjects = customerProjects;
    }
}
