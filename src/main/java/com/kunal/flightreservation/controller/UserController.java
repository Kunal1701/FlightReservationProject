package com.kunal.flightreservation.controller;

import com.kunal.flightreservation.entities.User;
import com.kunal.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "login/registerUser";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        return "login/login";
    }

    @RequestMapping("/registerUser")
    public String register(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {

        User user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)) {
            return "findFlights";
        } else {
            modelMap.addAttribute("msg", "Invalid username or password. Please try again.");
        }
        return "login/login";
    }
}
