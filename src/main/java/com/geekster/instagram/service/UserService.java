package com.geekster.instagram.service;

import com.geekster.instagram.dao.UserRepo;
import com.geekster.instagram.model.User;
import com.geekster.instagram.util.UserUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public int saveUser(User user){
        User userObject=userRepo.save(user);
        return userObject.getUserId();
    }

    public JSONArray getUser(String userId) {
        JSONArray userArray=new JSONArray();

         if (userId !=null && userRepo.findById(Integer.valueOf(userId)).isPresent()){
             User user=userRepo.findById(Integer.valueOf(userId)).get();
             JSONObject jsonObject = setUser(user);
             userArray.put(jsonObject);
         }
         else {
             List<User> userList=userRepo.findAll();
             for (User user:userList){
                 JSONObject jsonObject=setUser(user);
                 userArray.put(jsonObject);
             }
         }
         return userArray;
    }
    private JSONObject setUser(User user){
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("userId",user.getUserId());
        jsonObject.put("firstName",user.getFirstName());
        jsonObject.put("lastName",user.getLastName());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phoneNumber",user.getPhoneNumber());
        return jsonObject;
    }


    public ResponseEntity updateUser(User newUser, String userId) {
        if (userRepo.findById(Integer.valueOf(userId)).isPresent()){
            User user=userRepo.findById(Integer.valueOf(userId)).get();
            newUser.setUserId(user.getUserId());
            userRepo.save(newUser);
//            user.setAge(newUser.getAge());
//            user.setEmail(newUser.getEmail());
//            user.setPhoneNumber(newUser.getPhoneNumber());
//            user.setFirstName(newUser.getFirstName());
//            user.setLastName(newUser.getLastName());
//            userRepo.save(user);
            return new ResponseEntity("user update successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("user not found user id- "+userId,HttpStatus.BAD_REQUEST);
        }
    }
}
