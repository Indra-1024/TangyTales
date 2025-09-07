package com.cg.pickels.TangyTales.controller;

import com.cg.pickels.TangyTales.entity.AppUser;
import com.cg.pickels.TangyTales.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";  // login.html
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";  // register.html
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute AppUser user) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            return "redirect:/register?error=emailExists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/home")
    public String home() {
        return "home";  // home.html
    }

    // ✅ New mapping for showing users list
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users";  // users.html
    }
}
