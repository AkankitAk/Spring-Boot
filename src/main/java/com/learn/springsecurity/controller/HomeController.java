package com.learn.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public")
public class HomeController {
    @GetMapping(value = "/home")
    public String home(){
        return "This is home page";
    }
    @GetMapping(value = "/login")
    public String login(){
        return "This is login page";
    }
    @GetMapping(value = "/register")
    public String register(){
        return "This is register page";
    }

}
