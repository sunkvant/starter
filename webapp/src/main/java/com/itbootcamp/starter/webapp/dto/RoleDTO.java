package com.itbootcamp.starter.webapp.dto;

import javax.validation.constraints.NotNull;

public class RoleDTO {

    private Integer id;
    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
