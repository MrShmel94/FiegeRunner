package com.example.fiegerunner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/import")
    public String importFiles(){
        return "user/import";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }

    @GetMapping("/user")
    public String user() {
        return "user/user";
    }

    @GetMapping("/test")
    public String test() {
        return "user/header";
    }
}
