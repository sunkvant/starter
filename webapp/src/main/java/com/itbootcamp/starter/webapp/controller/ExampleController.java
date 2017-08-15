package com.itbootcamp.starter.webapp.controller;


import com.itbootcamp.starter.services.IUserInfoService;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.repository.ProjectEntityRepository;
import com.itbootcamp.starter.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ExampleController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ProjectEntityRepository projectEntityRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/")
    String home(Map<String, Object> model) {

        model.put("ans", "ds");
        return "welcome";
    }

}