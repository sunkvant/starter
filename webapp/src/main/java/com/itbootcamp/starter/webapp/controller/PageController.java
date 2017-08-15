package com.itbootcamp.starter.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by admin on 8/14/2017.
 */
@Controller
public class PageController {

    @RequestMapping("/profile")
    ModelAndView profile() {
        return new ModelAndView("profile");
    }
}
