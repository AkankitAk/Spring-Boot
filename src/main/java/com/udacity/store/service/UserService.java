package com.udacity.store.service;

import com.udacity.store.model.User;
import com.udacity.store.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String saveUser(User user) {
        User userObj = userRepository.save(user);
        return userObj.getUserId();
    }

    public JSONArray getUser(String userId) {

        JSONArray userArray = new JSONArray();

        if( null != userId && userRepository.findById(userId).isPresent()) {

            User user = userRepository.findById(userId).get();
            JSONObject userObj = setUser(user);
            userArray.put(userObj);
        } else {
            List<User> userList = userRepository.findAll();
            for (User user: userList) {
                JSONObject userObj = setUser(user);
                userArray.put(userObj);
            }
        }
        return userArray;
    }


    private JSONObject setUser (User user) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getUserId());
        jsonObject.put("userName", user.getUserName());
        jsonObject.put("address", user.getAddress());
        return jsonObject;

    }

    public void updateUser(User newUser, String userId) {

        if(userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            newUser.setUserId(user.getUserId());
            userRepository.save(newUser);
        }

    }
}