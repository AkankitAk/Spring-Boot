package com.geekster.chataaplication.controller;

import com.geekster.chataaplication.Util.CommonUtils;
import com.geekster.chataaplication.dao.StatusRepo;
import com.geekster.chataaplication.dao.UserRepo;
import com.geekster.chataaplication.model.Status;
import com.geekster.chataaplication.model.User;
import com.geekster.chataaplication.service.UserService;
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
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    UserRepo userRepo;
    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@RequestBody String newUser){
        //validation of user data converting to JSON and checking keys
        JSONObject isRequestValid=validateNewUser(newUser);
        int id;
        User user=new User();
        if(isRequestValid.isEmpty()){
            user=setUser(newUser);
            id=userService.saveUser(user);
        }
        else{
            return new ResponseEntity<>(isRequestValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("saved user id- "+id, HttpStatus.CREATED);
    }

    private User setUser(String newUser) {
         User user=new User();
         JSONObject jsonObject=new JSONObject(newUser);

         user.setEmail(jsonObject.getString("email"));
         user.setPhoneNumber(jsonObject.getString("phoneNumber"));
        if(!jsonObject.has("isUpdate")){
            user.setPassword(jsonObject.getString("password"));
        }
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
            if(userJson.has("isUpdate")){
                // check is user is already exist on database ?
                List<User> userList=userRepo.findByUsername(username);
                if(!userList.isEmpty()){
                    errorList.put("username","This user is already exist");
                    return errorList;
                }
            }

        }
        else {
            errorList.put("username","Missing parameter");
        }
        if(userJson.has("isUpdate")){
            if(userJson.has("password")){
                String password=userJson.getString("password");
                if(!CommonUtils.isValidPassword(password)){
                    errorList.put("password","Please enter valid password eg: Ankit@1234");
                }
            }
            else {
                errorList.put("password","Missing parameter");
            }
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


    @GetMapping(value = "/get")
    public ResponseEntity<String> getUsers(@Nullable @RequestParam String userId){
        JSONArray userArray=userService.getUser(userId);
        return new ResponseEntity<>(userArray.toString(),HttpStatus.OK);
    }


    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody String requestData){
        JSONObject isRequestValid=validateNewUser(requestData);
        User user=null;
        if (isRequestValid.isEmpty()){
            user=setUser(requestData);
            JSONObject responseObj=userService.updateUser(user,userId);
            if(responseObj.has("errorMessage")){
                return new ResponseEntity<>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>("User updated", HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>(isRequestValid.toString(),HttpStatus.OK);
        }
    }
    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(Integer.parseInt(userId));
        return new ResponseEntity<>("User deleted successfully user id- "+userId, HttpStatus.NO_CONTENT);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(@RequestBody String requestData){
        JSONObject jsonObj=new JSONObject(requestData);
        JSONObject isValidLogin=validLogin(jsonObj);
        if (isValidLogin.isEmpty()){
            String username=jsonObj.getString("username");
            String password=jsonObj.getString("password");
            JSONObject responseObj=userService.login(username,password);
            if(responseObj.has("errorMessage")){
                return new ResponseEntity<>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(responseObj.toString(), HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>(isValidLogin.toString(), HttpStatus.BAD_REQUEST);
        }
    }



    private JSONObject validLogin(JSONObject requestData) {

        JSONObject errorList=new JSONObject();
        if(!requestData.has("username")){
            errorList.put("username","Missing parameter");
        }
        if(!requestData.has("password")){
            errorList.put("password","Missing parameter");
        }
        return errorList;
    }
}















