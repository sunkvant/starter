package com.itbootcamp.starter.webapp.dto;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Iterator;
import java.util.List;

@Component
public class ValidatorList implements Validator {


    private Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ProfileDTO profileDTO=(ProfileDTO) target;
        for(int i=0; i<profileDTO.getEducations().size(); i++) {

            validator.validate(profileDTO.getEducations().get(i), errors);

        }

    }

//setters and getters

}