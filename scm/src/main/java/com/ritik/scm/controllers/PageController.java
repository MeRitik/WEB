package com.ritik.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ritik.scm.entities.User;
import com.ritik.scm.forms.UserForm;
import com.ritik.scm.helpers.Message;
import com.ritik.scm.helpers.MessageType;
import com.ritik.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String postMethodName(@ModelAttribute UserForm userform, HttpSession session) {
        // System.out.println(userform);
        // Validate the userForm
        // Save to the database

        /*
         * User user = User.builder()
         * .name(userform.getName())
         * .email(userform.getEmail())
         * .password(userform.getPassword())
         * .phoneNumber(userform.getPhoneNumber())
         * .about(userform.getAbout())
         * .profilePic(
         * "https://raw.githubusercontent.com/MeRitik/WEB/main/scm/src/main/resources/static/images/phonebook.png")
         * .build();
         */

        User user = new User(); // Builder does not work with default values
        user.setName(userform.getName());
        user.setEmail(userform.getEmail());
        user.setPassword(userform.getPassword());
        user.setPhoneNumber(userform.getPhoneNumber());
        user.setAbout(userform.getAbout());
        user.setProfilePic(
                "https://raw.githubusercontent.com/MeRitik/WEB/main/scm/src/main/resources/static/images/phonebook.png");

        User savedUser = userService.saveUser(user);
        // System.out.println(savedUser);

        // add the message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";
    }

}
