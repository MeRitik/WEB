package com.ritik.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    // dashboard
    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // User profile page
    @RequestMapping("/profile")
    public String userProfile() {
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
