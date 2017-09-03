package com.itbootcamp.starter.webapp.controller;

import com.itbootcamp.starter.datamodel.*;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.services.impl.*;
import com.itbootcamp.starter.webapp.dto.*;
import com.itbootcamp.starter.webapp.dto.factory.impl.DTOFactory;
import com.itbootcamp.starter.webapp.dto.factory.impl.EntityFactory;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.spec.OAEPParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by admin on 8/14/2017.
 */
@RestController
public class ProfileController {

    @Autowired
    private PersonService personService;

    @Autowired
    private DTOFactory dtoFactory;

    @Autowired
    private EntityFactory entityFactory;

    @Autowired
    private PersonRepository personRepository;

    private static final Logger logger = Logger.getLogger(ProfileController.class);

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView test(Model model) throws URISyntaxException {

        String ans="World";

        model.addAttribute("ans","World");

        System.out.println(getClass().getClassLoader().getResource("static/avatar/").toURI().getPath());

        return new ModelAndView("welcome");

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/api/avatar/upload", method=RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestPart MultipartFile file,OAuth2Authentication oAuth2Authentication) throws URISyntaxException {

                PersonEntity personEntity=personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());
                File pathToAvatar=new File(getClass().getClassLoader().getResource("static/avatar/").toURI().getPath()+System.currentTimeMillis()+".jpg");

        if (!file.isEmpty()) {

            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(pathToAvatar));
                stream.write(bytes);
                stream.close();
                ContactEntity contactEntity=personEntity.getContact();
                contactEntity.setAvatarPath("https://starter-itbootcamp.herokuapp.com/avatar/"+pathToAvatar.getName());
                personService.update(personEntity,contactEntity);
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.GET)
    ResponseEntity getProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtoFactory.getProfileDTO(personEntity), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile", method = RequestMethod.GET)
    ResponseEntity getProfileByLogin(@RequestParam(value = "login", required = false) String login,
                                     OAuth2Authentication oAuth2Authentication) {

        PersonEntity personEntity;

        if (login!=null) {

            personEntity = personService.getByLogin(login);

        } else {

            personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        }



        if (personEntity == null) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }


        return new ResponseEntity<>(dtoFactory.getProfileDTO(personEntity), HttpStatus.OK);
    }


    @RequestMapping(value = "/api/checklogin/{login}", method = RequestMethod.GET)
    ResponseEntity getProfileByLogin(@PathVariable String login) {


        if (personService.getByLogin(login)!=null) {

            return new ResponseEntity<>(true,HttpStatus.OK);

        } else {


            return new ResponseEntity<>(false,HttpStatus.OK);
        }


    }



    @RequestMapping(value = "/api/profile/", method = RequestMethod.POST)
    ResponseEntity createProfile(@RequestBody @Valid ProfileDTO profileDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


            if (!personService.create(entityFactory.getPersonEntity(profileDTO))) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

        return new ResponseEntity (HttpStatus.CREATED);

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/api/profile/", method = RequestMethod.PUT)
    ResponseEntity<ProfileDTO> updateProfile(@RequestBody @Valid ContactDTO contactDTO, BindingResult bindingResult,
                                             OAuth2Authentication oAuth2Authentication) {
        PersonEntity personEntity;

        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


        personEntity = personService.getByLogin(oAuth2Authentication.getUserAuthentication().getName());

        if (!personService.update(personEntity,entityFactory.getContactEntity(contactDTO))) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping(value = "/api/profile/{personId}", method = RequestMethod.DELETE)
    ResponseEntity<ProfileDTO> deleteProfile(@PathVariable Integer personId) {

        PersonEntity personEntity = personService.getById(personId);

        if (personEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        personRepository.delete(personId);

        return new ResponseEntity<>(HttpStatus.OK);

    }



}