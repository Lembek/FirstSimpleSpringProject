package com.example.MyTwitter.controllers;


import com.example.MyTwitter.entities.User;
import com.example.MyTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class MainController {
    private final UserService userService;


    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String main() {
        return "main";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute User user) {
        return "registration";
    }


    @PostMapping("/registration")
    public String newUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors())
            return "registration";

        userService.save(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
