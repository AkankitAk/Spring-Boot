package com.geekster.chataaplication.service;

import com.geekster.chataaplication.dao.UserRepo;
import com.geekster.chataaplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    public int saveUser(User user) {
        User userObj=userRepo.save(user);
        return userObj.getUserId();
    }
}
