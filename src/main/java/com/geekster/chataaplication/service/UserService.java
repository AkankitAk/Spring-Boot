package com.geekster.chataaplication.service;

import com.geekster.chataaplication.dao.UserRepo;
import com.geekster.chataaplication.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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


    public JSONObject login(String username, String password) {
        JSONObject response=new JSONObject();
        List<User> user= userRepo.findByUsername(username);
        if(user.isEmpty()){
            response.put("errorMessage","Username doesn't exists");
            return response;
        }
        else {
            // get the user in 0th index
            User userObj=user.get(0);
            if (password.equals(userObj.getPassword())){
                response= createResponse(userObj);
            }else {
                response.put("password","password is not valid");
            }
        }
        return response;
    }

    public JSONObject updateUser(User user,String userId) {
        JSONObject obj=new JSONObject();
        List<User> userList=userRepo.getUserByUserId(Integer.parseInt(userId));
        if (!userList.isEmpty()){
            User oldUser=userList.get(0);
            user.setUserId(oldUser.getUserId());
            user.setCreatedDate(oldUser.getCreatedDate());
            user.setPassword(oldUser.getPassword());
            Timestamp updateTime=new Timestamp(System.currentTimeMillis());
            user.setUpdatedDate(updateTime);
            userRepo.save(user);
        }
        else {
            obj.put("errorMessage","User doesn't exist");
        }
        return obj;
    }
}
