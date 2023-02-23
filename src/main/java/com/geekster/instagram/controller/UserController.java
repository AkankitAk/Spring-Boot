package com.geekster.instagram.controller;


import com.geekster.instagram.model.User;
import com.geekster.instagram.service.UserService;


import jakarta.annotation.Nullable;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/user")
    public ResponseEntity saveUser(@RequestBody String  userData){
        User user=setUser(userData);
        int userId=userService.saveUser(user);
        return new ResponseEntity("User save with id -"+userId, HttpStatus.CREATED);
    }


    @GetMapping(value = "/user")
    public ResponseEntity getUser(@Nullable @RequestParam String userId){
        JSONArray userDetails=userService.getUser(userId);
        return new ResponseEntity(userDetails.toString(),HttpStatus.OK);
    }


    private User setUser(String userData) {
        JSONObject jsonObject=new JSONObject(userData);
        User user=new User();
        user.setAge(jsonObject.getInt("age"));
        user.setEmail(jsonObject.getString("email"));
        user.setFirstName(jsonObject.getString("firstName"));
        user.setLastName(jsonObject.getString("lastName"));
        user.setPhoneNumber(jsonObject.getString("phoneNumber"));
        return user;
    }


    @PutMapping(value = "/user/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId,@RequestBody String userData){
        User user=setUser(userData);
        return userService.updateUser(user,userId);
    }

}
