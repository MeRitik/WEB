package com.ritik.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ritik.scm.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // dashboard
    @RequestMapping("/dashboard")
    public String userDashboard(Authentication authentication) {
        String email = Helper.getEmailOfLoggedInUser(authentication);
        logger.info(email);
        // Fetching data from db with email
        return "user/dashboard";
    }

    // User profile page
    @RequestMapping("/profile")
    public String userProfile(Authentication authentication) {
        String email = Helper.getEmailOfLoggedInUser(authentication);
        logger.info(email);
        // Fetching data from db with email
        return "user/profile";
    }

    // add contact
    @RequestMapping("/add")
    public String addContact() {
        return "user/add";
    }
    // user view
    // user edit contact
    // user delete contact
}
