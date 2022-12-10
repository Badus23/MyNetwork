package com.mamchura.socialNetwork.controllers;

import com.mamchura.socialNetwork.models.Message;
import com.mamchura.socialNetwork.models.User;
import com.mamchura.socialNetwork.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private MessageRepository repository;
    @GetMapping("/")
    public String greeting() {
        return "start";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        if(filter != null && !filter.isEmpty()) {
            model.addAttribute("messages", repository.findByTag(filter));
        } else {
            model.addAttribute("messages", repository.findAll());
        }
        model.addAttribute("filter", filter);
        return "messages";
    }

    @PostMapping("/post")
    public String post(@AuthenticationPrincipal User user,
                       @Valid Message message,
                       BindingResult bindingResult,
                       Model model,
                       @RequestParam("file") MultipartFile file) throws IOException {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = UtilController.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File dir = new File(uploadPath);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                String UID = UUID.randomUUID().toString();
                String resultName = UID + "-" + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultName));

                message.setFilename(resultName);
            }
            model.addAttribute("message", null);
            repository.save(message);
        }
        model.addAttribute("messages", repository.findAll());
        return "messages";
    }
}
