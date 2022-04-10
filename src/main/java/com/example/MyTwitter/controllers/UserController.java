package com.example.MyTwitter.controllers;

import com.example.MyTwitter.entities.Message;
import com.example.MyTwitter.entities.User;
import com.example.MyTwitter.services.MessageService;
import com.example.MyTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public String getList(Model model,
                          @ModelAttribute Message message,
                          @RequestParam(required = false) String filter) {
        List<Message> list = filter != null ? messageService.findAllByTag(filter) : messageService.findAll();
        model.addAttribute("messages", list);
        return "messages";
    }

    @PostMapping("/messages")
    public String addNewMessage(@Valid @ModelAttribute Message message, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors())
            return "messages";

        messageService.save(message, userService.findByUsername(principal.getName()));
        return "redirect:/messages";
    }

    @GetMapping("/profile")
    public String myProfile(Principal principal, Model model) {
        User myself = userService.findByUsername(principal.getName());
        model.addAttribute("myself", myself);
        model.addAttribute("messageList", messageService.findAllByAuthor(myself));
        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable String id, Principal principal, Model model) {
        User myself = userService.findByUsername(principal.getName());
        Long userId = Long.parseLong(id);
        if (myself.getId().equals(userId))
            return "redirect:/profile";
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("messages", messageService.findAllByAuthor(user));
        return "profilePage";
    }

    @GetMapping("profile/edit")
    public String getEditPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("myself", user);
        return "edit";
    }

    @PatchMapping("/profile/edit")
    public String edit(User newUser, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        userService.saveChanges(user, newUser);
        return "redirect:/profile";
    }

    @DeleteMapping("/profile/delete/{id}")
    public String delete(@PathVariable String id) {
        messageService.deleteById(Long.parseLong(id));
        return "redirect:/profile";
    }
}
