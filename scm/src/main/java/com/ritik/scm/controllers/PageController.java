package com.ritik.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ritik.scm.forms.UserForm;
import com.ritik.scm.services.UserService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("name", "ABC Technologies");
        return "HOME";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        // userForm.setName("ritik");
        // userForm.setEmail("akjdljf@kjf.com");
        // userForm.setPhoneNumber("kjfkdjf");
        // userForm.setAbout("khfkjhdfhfhfhfhfhfhfhfhfhfhfhfhfhfhfhfhfhf");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @PostMapping("/do-register")
    public String postMethodName(@ModelAttribute UserForm userform) {
        System.out.println(userform);
        return "redirect:/register";
    }

}
