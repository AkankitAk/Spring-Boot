package com.geekster.chataaplication.service;

import com.geekster.chataaplication.dao.UserRepo;
import com.geekster.chataaplication.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    public int saveUser(User user) {
        User userObj=userRepo.save(user);
        return userObj.getUserId();
    }

    public JSONArray getUser(String userId) {
        JSONArray response=new JSONArray();
        if(userId !=null){
            List<User> userList= userRepo.getUserByUserId(Integer.parseInt(userId));
            for (User user:userList) {
                //I send to customize output that's why use this createResponse method
                JSONObject userObj=createResponse(user);
                response.put(userObj);
            }
        }else {
            List<User> userList= userRepo.getAllUser();
            for (User user:userList) {
                JSONObject userObj=createResponse(user);
                response.put(userObj);
            }
        }
        return response;
    }

    private JSONObject createResponse(User user) {
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("userId",user.getUserId());
        jsonObj.put("username",user.getUsername());
        jsonObj.put("firstName",user.getFirstName());
        jsonObj.put("lastName",user.getLastName());
        jsonObj.put("age",user.getAge());
        jsonObj.put("email",user.getEmail());
        jsonObj.put("phoneNumber",user.getPhoneNumber());
        jsonObj.put("gender",user.getGender());
        jsonObj.put("createdDate",user.getCreatedDate());
        return jsonObj;
    }

    public void deleteUser(String userId) {

    }
}
