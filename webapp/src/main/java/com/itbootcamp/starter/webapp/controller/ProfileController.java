package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.RoleType;
import com.itbootcamp.starter.services.impl.PersonService;
import com.itbootcamp.starter.webapp.dto.AbstractPersonDTO;
import com.itbootcamp.starter.webapp.dto.DTOFactory;
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
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.GET)
    ResponseEntity<AbstractPersonDTO> getProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        if ((personEntity.getRole().getName().equals(RoleType.ROLE_ADMIN)) || (personEntity.getRole().getName().equals(RoleType.ROLE_CUSTOMER)) || (personEntity.getRole().getName().equals(RoleType.ROLE_MODER))) {

            return new ResponseEntity<AbstractPersonDTO>(dtoFactory.getCustomerAdminModerDTO(personEntity), HttpStatus.OK);

        }
        if (personEntity.getRole().getName().equals(RoleType.ROLE_TRAINEE)) {


            return new ResponseEntity<AbstractPersonDTO>(dtoFactory.getTraineeDTO(personEntity), HttpStatus.OK);



        } else {


            return new ResponseEntity<AbstractPersonDTO>(dtoFactory.getMentorDTO(personEntity), HttpStatus.OK);

        }



    }

}