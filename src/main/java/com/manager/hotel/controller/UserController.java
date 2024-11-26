package com.manager.hotel.controller;

import com.manager.hotel.model.User;
import com.manager.hotel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public String viewRegister(Model model) {
        model.addAttribute("activePage", "register");
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute User user, BindingResult result, Model model) {
        User existing = userService.loadUserByUsername(user.getUsername());
        if (existing != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("usernameErrorMsg", "Username is already registered!");
            return "register";
        }
        existing = userService.loadUserByEmail(user.getEmail());
        if (existing != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("emailErrorMsg", "Email is already registered!");
            return "register";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String viewLogin(Model model) {
        model.addAttribute("activePage", "login");
        return "login";
    }
}
