package com.geekster.instagram.service;

import com.geekster.instagram.dao.UserRepo;
import com.geekster.instagram.model.User;
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

//    save the user detail which come from the front end
    public int saveUser(User user){
        User userObject=userRepo.save(user);
        return userObject.getUserId();
    }

//    get user by user id and if not give user id then send all user
    public JSONArray getUser(String userId) {
        JSONArray userArray=new JSONArray();
//        check is user is present or not
         if (userId !=null && userRepo.findById(Integer.valueOf(userId)).isPresent()){
             List<User> userList=userRepo.findByUserId(Integer.valueOf(userId));
             for (User users:userList) {
                 JSONObject jsonObject = setUser(users);
                 userArray.put(jsonObject);
             }
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
        jsonObject.put("userName",user.getUserName());
        jsonObject.put("password",user.getPassword());
        jsonObject.put("age",user.getAge());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phoneNumber",user.getPhoneNumber());
        return jsonObject;
    }

//    user update by user id and user send which data will update
    public JSONObject updateUser(User newUser, String userId) {
        JSONObject jsonObject=new JSONObject();
        User user=userRepo.findById(Integer.valueOf(userId)).get();
//        check user is present or not
        if (user!=null){
            User oldUser=user;
            newUser.setUserId(oldUser.getUserId());

            newUser.setPassword(oldUser.getPassword());
            newUser.setCreateDate(oldUser.getCreateDate());
            Timestamp updateDate=new Timestamp(System.currentTimeMillis());
            newUser.setUpdateDate(updateDate);
            userRepo.save(newUser);
        }
        else {
            jsonObject.put("errorMessage","User doesn't exist");
        }
        return jsonObject;
    }

    public void deleteUserById(int userId) {
        userRepo.deleteUserById(userId);
    }
}
