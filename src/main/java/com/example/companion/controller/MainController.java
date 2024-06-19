package com.example.companion.controller;

import com.example.companion.command.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(@ModelAttribute("loginCommand")LoginCommand loginCommand){
        return "index";
    }
}
