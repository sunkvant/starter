package com.itbootcamp.starter.webapp.dto;

import com.itbootcamp.starter.datamodel.impl.ContactEntity;
import com.itbootcamp.starter.datamodel.impl.MentorInfoEntity;
import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.ProfileEntity;

/**
 * Created by admin on 8/15/2017.
 */
public class Converter {

    public static AbstractPersonDTO convert(PersonEntity personEntity) {
        switch (personEntity.getRole().getName()) {
            case "Trainee": {
                TraineeDTO traineeDTO = new TraineeDTO();
                ContactEntity contactEntity = personEntity.getContact();
                ProfileEntity profileEntity = personEntity.getProfile();
                traineeDTO.setLogin(personEntity.getLogin());
                traineeDTO.setFullName(contactEntity.getFullName());
                traineeDTO.setDateOfBirth(contactEntity.getDateOfBirth());
                traineeDTO.setAvatarPath(contactEntity.getAvatarPath());
                traineeDTO.setPhone(contactEntity.getPhone());
                traineeDTO.setSkype(contactEntity.getSkype());
                traineeDTO.setEmail(contactEntity.getEmail());
                traineeDTO.setAbout(contactEntity.getAbout());
                traineeDTO.setRoleName(personEntity.getRole().getName());
                traineeDTO.setBlocked(personEntity.getBlocked());
                traineeDTO.setDirectionName(profileEntity.getDirection().getName());
                traineeDTO.setApproved(profileEntity.getApproved());
                traineeDTO.setCourses(profileEntity.getCourses());
                traineeDTO.setWorkplaces(profileEntity.getWorkplaces());
                traineeDTO.setEducations(profileEntity.getEducations());
                traineeDTO.setSkills(profileEntity.getSkills());
                return traineeDTO;
            }
            case "Mentor": {
                MentorDTO mentorDTO = new MentorDTO();
                ContactEntity contactEntity = personEntity.getContact();
                ProfileEntity profileEntity = personEntity.getProfile();
                MentorInfoEntity mentorInfoEntity = personEntity.getMentorInfo();
                mentorDTO.setLogin(personEntity.getLogin());
                mentorDTO.setFullName(contactEntity.getFullName());
                mentorDTO.setDateOfBirth(contactEntity.getDateOfBirth());
                mentorDTO.setAvatarPath(contactEntity.getAvatarPath());
                mentorDTO.setPhone(contactEntity.getPhone());
                mentorDTO.setSkype(contactEntity.getSkype());
                mentorDTO.setEmail(contactEntity.getEmail());
                mentorDTO.setAbout(contactEntity.getAbout());
                mentorDTO.setRoleName(personEntity.getRole().getName());
                mentorDTO.setBlocked(personEntity.getBlocked());
                mentorDTO.setDirectionName(profileEntity.getDirection().getName());
                mentorDTO.setApproved(profileEntity.getApproved());
                mentorDTO.setCourses(profileEntity.getCourses());
                mentorDTO.setWorkplaces(profileEntity.getWorkplaces());
                mentorDTO.setEducations(profileEntity.getEducations());
                mentorDTO.setSkills(profileEntity.getSkills());
                mentorDTO.setMentorExp(mentorInfoEntity.getMentorExp());
                mentorDTO.setExperience(mentorInfoEntity.getExperience());
                return mentorDTO;
            }
            case "Customer": {
                CustomerDTO customerDTO = new CustomerDTO();
                ContactEntity contactEntity = personEntity.getContact();
                customerDTO.setLogin(personEntity.getLogin());
                customerDTO.setFullName(contactEntity.getFullName());
                customerDTO.setDateOfBirth(contactEntity.getDateOfBirth());
                customerDTO.setAvatarPath(contactEntity.getAvatarPath());
                customerDTO.setPhone(contactEntity.getPhone());
                customerDTO.setSkype(contactEntity.getSkype());
                customerDTO.setEmail(contactEntity.getEmail());
                customerDTO.setAbout(contactEntity.getAbout());
                customerDTO.setRoleName(personEntity.getRole().getName());
                customerDTO.setBlocked(personEntity.getBlocked());
                customerDTO.setCustomerProjects(personEntity.getCustomerProjects());
                return customerDTO;
            }
            case "Admin":
            case "Moder": {
                AbstractPersonDTO adminDTO = new AbstractPersonDTO();
                ContactEntity contactEntity = personEntity.getContact();
                adminDTO.setLogin(personEntity.getLogin());
                adminDTO.setFullName(contactEntity.getFullName());
                adminDTO.setDateOfBirth(contactEntity.getDateOfBirth());
                adminDTO.setAvatarPath(contactEntity.getAvatarPath());
                adminDTO.setPhone(contactEntity.getPhone());
                adminDTO.setSkype(contactEntity.getSkype());
                adminDTO.setEmail(contactEntity.getEmail());
                adminDTO.setAbout(contactEntity.getAbout());
                adminDTO.setRoleName(personEntity.getRole().getName());
                adminDTO.setBlocked(personEntity.getBlocked());
                return adminDTO;
            }
        }

        return null;
    }
}


