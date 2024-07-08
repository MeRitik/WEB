package com.ritik.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ritik.scm.entities.User;
import com.ritik.scm.helpers.Helper;
import com.ritik.scm.services.UserService;

@ControllerAdvice // will be executed for each request
public class RootController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // execute this method on each handler method of this class using
    // @MOdelAttribute
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null)
            return;
        logger.info("Adding logged in user information to the model");
        String email = Helper.getEmailOfLoggedInUser(authentication);
        logger.info(email);
        // Fetching data from db with email
        User user = userService.getUserByEmail(email);
        // System.out.println(user);

        System.out.println(user);
        model.addAttribute("loggedInUser", user);
    }
}
