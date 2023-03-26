package com.learn.springsecurity.controller;

import com.learn.springsecurity.model.User;
import com.learn.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/get")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    // ye annotation se bhi hum role based authentication kr skte hai  iske liye hume MySecurityConfig mai hi ek
    // annotation lagana hoga @EnableGlobalMethodSecurity ka use krna hoga
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get/{userName}")
    public User getUser(@PathVariable String userName){
        return userService.getUserByName(userName);
    }
    @PostMapping(value = "/add")
    public User addUser(@RequestBody User newUser){
        return userService.addUser(newUser);
    }


}
