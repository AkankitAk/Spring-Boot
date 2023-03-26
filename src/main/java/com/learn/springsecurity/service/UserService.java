package com.learn.springsecurity.service;

import com.learn.springsecurity.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    List<User> list=new ArrayList<>();
    public UserService(){
        list.add(new User("abc","abc","abc@gmail.com"));
        list.add(new User("xyz","xyz","xyz@gmail.com"));
        list.add(new User("ankit","ankit","ankit@gmail.com"));
    }
    public List<User> getAllUser(){
        return this.list;
    }
    public User getUserByName(String userName){
        // lemda expression
        return this.list.stream().filter(user -> user.getUserName().equals(userName)).findAny().orElse(null);
    }
    public User addUser(User newUser){
        this.list.add(newUser);
        return newUser;
    }
}
