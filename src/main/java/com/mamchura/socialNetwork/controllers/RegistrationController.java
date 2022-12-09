package com.mamchura.socialNetwork.controllers;

import com.mamchura.socialNetwork.models.Role;
import com.mamchura.socialNetwork.models.User;
import com.mamchura.socialNetwork.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository repository;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User tempUser = repository.findByUsername(user.getUsername());
        if (tempUser != null) {
            model.put("message", "User is already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        repository.save(user);
        return "redirect:/login";
    }
}
