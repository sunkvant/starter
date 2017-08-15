package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.webapp.dto.AbstractDTO;
import com.itbootcamp.starter.webapp.dto.MentorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 8/14/2017.
 */
@RestController
public class ProfileController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/profile/{personId}",method = RequestMethod.GET)
    ResponseEntity<AbstractDTO> getMentorProfile(@PathVariable Integer personId){

        PersonEntity personEntity=personRepository.findOne(personId);

        MentorDTO mentorDTO=new MentorDTO();

        mentorDTO.setLogin(personEntity.getLogin());
        mentorDTO.setFullName(personEntity.getContact().getFullName());
        mentorDTO.setDateOfBirth(personEntity.getContact().getDateOfBirth());
        mentorDTO.setAvatarPath(personEntity.getContact().getAvatarPath());
        mentorDTO.setPhone(personEntity.getContact().getPhone());
        mentorDTO.setSkype(personEntity.getContact().getSkype());
        mentorDTO.setEmail(personEntity.getContact().getEmail());
        mentorDTO.setAbout(personEntity.getContact().getAbout());
        mentorDTO.setRoleName(personEntity.getRole().getName());
        mentorDTO.setBlocked(personEntity.getBlocked());

        mentorDTO.setDirectionName(personEntity.getProfile().getDirection().getName());
        mentorDTO.setApprowed(personEntity.getProfile().getApproved());
        mentorDTO.setCourses(personEntity.getProfile().getCourses());
        mentorDTO.setWorkplaces(personEntity.getProfile().getWorkplaces());
        mentorDTO.setEducations(personEntity.getProfile().getEducations());
        mentorDTO.setMentorExp(personEntity.getMentorInfo().getMentorExp());
        mentorDTO.setExperience(personEntity.getMentorInfo().getExperience());



        return new ResponseEntity<AbstractDTO>(mentorDTO, HttpStatus.OK);
    }

}
