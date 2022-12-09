package com.mamchura.socialNetwork.controllers;

import com.mamchura.socialNetwork.models.Message;
import com.mamchura.socialNetwork.models.User;
import com.mamchura.socialNetwork.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepository repository;
    @GetMapping("/")
    public String greeting() {
        return "start";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("messages", repository.findAll());
        return "messages";
    }

    @PostMapping("/post")
    public String post(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag) {
        repository.save(new Message(text, tag, user));
        return "redirect:/main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        if(filter != null && !filter.isEmpty()) {
            model.put("messages", repository.findByTag(filter));
        } else {
            model.put("messages", repository.findAll());
        }
        return "messages";
    }
}
