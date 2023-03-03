package com.geekster.chataaplication.controller;

import com.geekster.chataaplication.Util.CommonUtils;
import com.geekster.chataaplication.dao.StatusRepo;
import com.geekster.chataaplication.model.Status;
import com.geekster.chataaplication.model.User;
import com.geekster.chataaplication.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    StatusRepo statusRepo;
    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@RequestBody String newUser){
        JSONObject isValid=validateNewUser(newUser);
        int id;
        User user=new User();
        if(isValid.isEmpty()){
            user=setUser(newUser);
            id=userService.saveUser(user);
        }
        else{
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("saved user id- "+id, HttpStatus.CREATED);
    }

    private User setUser(String newUser) {
         User user=new User();
         JSONObject jsonObject=new JSONObject(newUser);

         user.setEmail(jsonObject.getString("email"));
         user.setPhoneNumber(jsonObject.getString("phoneNumber"));
         user.setPassword(jsonObject.getString("password"));
         user.setUsername(jsonObject.getString("username"));
         user.setFirstName(jsonObject.getString("firstName"));

         if(jsonObject.has("age")){
             user.setAge(jsonObject.getInt("age"));
         }
         if(jsonObject.has("lastName")){
             user.setLastName(jsonObject.getString("lastName"));
         }
         if(jsonObject.has("gender")){
             user.setGender(jsonObject.getString("gender"));
         }


         Timestamp createTime=new Timestamp(System.currentTimeMillis());
         user.setCreatedDate(createTime);

         Status status= statusRepo.findById(1).get();

        user.setStatusId(status);
         return user;
    }

    private JSONObject validateNewUser(String newUser) {
        JSONObject userJson=new JSONObject(newUser);
        JSONObject errorList=new JSONObject();


        if(userJson.has("username")){
            String username=userJson.getString("username");
        }
        else {
            errorList.put("username","Missing parameter");
        }
        if(userJson.has("password")){
            String password=userJson.getString("password");
            if(!CommonUtils.isValidPassword(password)){
                errorList.put("password","Please enter valid password eg: Ankit@1234");
            }
        }
        else {
            errorList.put("password","Missing parameter");
        }
        if(userJson.has("firstName")){
            String firstName=userJson.getString("firstName");
        }
        else {
            errorList.put("firstName","Missing parameter");
        }
        if(userJson.has("email")){
            String email=userJson.getString("email");
            if (!CommonUtils.isValidEmail(email)){
                errorList.put("email","Please enter valid email eg:abcd@gmail.com ");
            }
        }
        else {
            errorList.put("email","Missing parameter");
        }
        if(userJson.has("phoneNumber")){
            String phoneNumber=userJson.getString("phoneNumber");
            if (!CommonUtils.isValidPhoneNumber(phoneNumber)){
                errorList.put("phoneNumber","Please enter valid phoneNumber ");
            }
        }
        else {
            errorList.put("phoneNumber","Missing parameter");
        }
        if(userJson.has("age")){
            String age=userJson.getString("age");
        }
        if(userJson.has("lastName")){
            String lastName=userJson.getString("lastName");
        }
        return errorList;

    }


}















