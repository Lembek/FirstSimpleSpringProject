package com.example.MyTwitter.controllers;

import com.example.MyTwitter.entities.User;
import com.example.MyTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @PatchMapping("/edit/{id}")
    public String addRole(@PathVariable String id) {
        User user = userService.findById(Long.parseLong(id));
        userService.addAdminRole(user);
        return "redirect:/admin";
    }
}
