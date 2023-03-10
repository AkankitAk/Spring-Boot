package com.geekster.instagram.controller;


import com.geekster.instagram.dao.SecurityRepo;
import com.geekster.instagram.dao.StatusRepo;
import com.geekster.instagram.dao.UserRepo;
import com.geekster.instagram.model.Security;
import com.geekster.instagram.model.Status;
import com.geekster.instagram.model.User;
import com.geekster.instagram.service.UserService;
import com.geekster.instagram.util.CommonUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    StatusRepo statusRepo;
    @Autowired
    SecurityRepo securityRepo;

    @Autowired
    UserRepo userRepo;
//    crete a new user
    @PostMapping(value = "/user")
    public ResponseEntity saveUser(@RequestBody String  userData){

        JSONObject isValidateUser=isValidateNewUser(userData);
        User user=new User();
        int id ;
        if(isValidateUser.isEmpty()) {
            user = setUser(userData);
            id=userService.saveUser(user);
        }
        else {
            return new ResponseEntity<>(isValidateUser.toString(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("User save with id -"+id, HttpStatus.CREATED);
    }

    private JSONObject isValidateNewUser(String userData) {
        JSONObject json=new JSONObject(userData);
        JSONObject errorList=new JSONObject();

        if(json.has("userName")){
            String userName=json.getString("userName");

            if(json.has("isUpdate")){
                List<User> userList=userRepo.findByUserName(userName);
                if(!userList.isEmpty()){
                    errorList.put("userName ","This user is already exist");
                    return errorList;
                }
            }
        }
        else {
            errorList.put("userName","Missing parameter");
        }
        if(json.has("password")){
            String password=json.getString("password");
            if(!CommonUtil.validPassword(password)){
 ;               errorList.put("password","please enter valid password like this : Ankit@123");
            }
        }
        else {
            errorList.put("password","Missing parameter");
        }
        if (json.has("phoneNumber")){
            String phoneNumber=json.getString("phoneNumber");
            if(!CommonUtil.isValidPhoneNumber(phoneNumber)){
                errorList.put("phoneNumber","Please enter valid PhoneNumber");
            }
        }
        else {
            errorList.put("phoneNumber","Missing parameter");
        }
        if (json.has("email")){
            String email=json.getString("email");
            if(!CommonUtil.isValidEmail(email)){
                errorList.put("email","please enter valid email like this :ankit@gmail.com");
            }
        }
        else {
            errorList.put("email","Missing parameter");
        }
        if (!json.has("firstName")){
            errorList.put("firstName","Missing parameter");
        }

        return errorList;
    }

//    convert String user data to json object and return user
    private User setUser(String userData) {
        JSONObject jsonObject=new JSONObject(userData);
        User user=new User();

        user.setUserName(jsonObject.getString("userName"));
        user.setPassword(jsonObject.getString("password"));
        user.setFirstName(jsonObject.getString("firstName"));
        user.setLastName(jsonObject.getString("lastName"));
        user.setEmail(jsonObject.getString("email"));
        user.setPhoneNumber(jsonObject.getString("phoneNumber"));
        user.setAge(jsonObject.getInt("age"));

        Timestamp createDate=new Timestamp(System.currentTimeMillis());
        user.setCreateDate(createDate);

        Status status=statusRepo.findById(1).get();
        user.setStatusId(status);

        Security security=securityRepo.findById(1).get();
        user.setSecurityId(security);

        return user;
    }
//    get user by user id and if we not give the user id then all user will come
    @GetMapping(value = "/user")
    public ResponseEntity<String> getUser(@Nullable @RequestParam String userId){
        JSONArray userDetails=userService.getUser(userId);
        return new ResponseEntity<>(userDetails.toString(),HttpStatus.OK);
    }

//    update user by user id
    @PutMapping(value = "/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId,@RequestBody String requestData){
        JSONObject isValidateUser=isValidateNewUser(requestData);
        User user=null;
        int id ;
        if(isValidateUser.isEmpty()) {
            user=setUser(requestData);
            JSONObject responseObj=userService.updateUser(user,userId);
            if(responseObj.has("errorMessage")){
                return new ResponseEntity<>(responseObj.toString(),HttpStatus.BAD_REQUEST);
            }
            else {
                return new ResponseEntity<>("user Updated",HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(isValidateUser.toString(),HttpStatus.OK);
    }


// delete user by user id
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        userService.deleteUserById(Integer.parseInt(userId));
        return new ResponseEntity<>("user deleted",HttpStatus.NO_CONTENT);
    }

}
